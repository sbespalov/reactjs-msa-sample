package ru.sbrf.basel.integration.gateway.brd;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.integration.launch.JobLaunchRequest;
import org.springframework.batch.integration.launch.JobLaunchingGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.file.DefaultFileNameGenerator;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.jdbc.ExpressionEvaluatingSqlParameterSourceFactory;
import org.springframework.integration.jdbc.JdbcOutboundGateway;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import ru.sbrf.basel.integration.gateway.brd.batch.BrdBatchConfiguration;

@Configuration
@Import(BrdBatchConfiguration.class)
public class BrdConfiguration
{

    private static final String QUERY_UPDATE_BOOK_FIXML_BATCH_STATUS = "UPDATE buf.tFixml SET IsComplete = 1 WHERE FixmlId = :FixmlId";

    private static final String QUERY_SELECT_BOOK_FIXML_BATCH = "SELECT t.FixmlId, t.Value FROM buf.tFixml t WHERE t.MessageType='BOOK' and t.IsComplete = 0";

    @Value("${basel.integration.brd.bookFixmlStorageFolder}")
    private String bookFixmlStorageFolder;

    @Autowired
    private Job parseBookFxmlJob;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private DataSource bookFxmlBufferDataSource;

    @Bean
    public Unmarshaller bookFixmlMarshaller()
    {
        Jaxb2Marshaller result = new Jaxb2Marshaller();
        result.setPackagesToScan("ru.trd.msk.svn.xsd.fixml");
        return result;
    }

    @Bean
    public DataSource bookFxmlBufferDataSource(@Value("${basel.integration.brd.jdbcUrl}") String jdbcUrl,
                                               @Value("${basel.integration.brd.jdbcUser}") String jdbcUser,
                                               @Value("${basel.integration.brd.jdbcPassword}") String jdbcPassword)
    {
        ComboPooledDataSource result = new ComboPooledDataSource();
        result.setJdbcUrl(jdbcUrl);
        result.setUser(jdbcUser);
        result.setPassword(jdbcPassword);
        return result;
    }

    /**
     * Описание основного процесса загрузки "Буков" <br>
     * <br>
     * <b>Step1:</b> выборка пачки FIXML из таблицы (запрос выполняется с заданной переодичностью) <br>
     * <b>Step2:</b> разделение результатов выборки на отдельные FIXML документы <br>
     * <b>Step3:</b> запись FIXML во временный буфферный файл <br>
     * <b>Step4:</b> разделение потоков <br>
     * &emsp;<b>Step 4.1:</b> отмечаем FIXML документ в буфферной таблице как обработанный <br>
     * &emsp;<b>Step 4.2:</b> запускаем параллельный парсинг и обработку пачек Буков <br>
     * &emsp;&emsp;<b>Step 4.2.1:</b> Формирование задачи для параллельной обработки пачек "Буков" <br>
     * &emsp;&emsp;<b>Step 4.2.2:</b> Запуск задачи для параллельной обработки пачек "Буков"
     * 
     * 
     * @return
     * @throws IOException
     */
    @Bean
    public IntegrationFlow brdImportFlow()
        throws IOException
    {
        return IntegrationFlows.from(// Step1
                                     bookFxmlPollingChannelAdapter(),
                                     e -> e.poller(Pollers.fixedRate(1000).maxMessagesPerPoll(10)))
                               .channel(new DirectChannel())
                               // Step2
                               .split(List.class, s -> this.extractBookFixmlItems(s))
                               .channel(new DirectChannel())
                               // Step3
                               .handle(fileWritingMessageHandler())
                               // Step4
                               .publishSubscribeChannel(s -> s.subscribe(// Step 4.1
                                                                         f -> f.handle(bookFxmlBooferOutboundGateway()))
                                                              .subscribe(// Step 4.2
                                                                         f -> f.transform(// Step 4.2.1
                                                                                          this::bookFixmlMessageToBookJobRequest)
                                                                               .channel(new DirectChannel())
                                                                               // Step 4.2.2
                                                                               .handle(bookProcessingGateway())
                                                                               .channel("nullChannel")))
                               .get();
    }

    /**
     * Описание процесса завершения загрузки Буков <br>
     * <b>Step1:</b> Удаление временных буфферных FIXML файлов
     * 
     * @return
     */
    @Bean
    public IntegrationFlow brdFixmlParseCompleateFlow()
    {
        return IntegrationFlows.from(bookFixmlParseCompleateOuputChannel())
                               // Step1
                               .handle(m -> this.bookFixmlFileDelete(m))
                               .get();
    }

    @Bean
    public MessageSource<Object> bookFxmlPollingChannelAdapter()
    {
        JdbcPollingChannelAdapter result = new JdbcPollingChannelAdapter(bookFxmlBufferDataSource,
                QUERY_SELECT_BOOK_FIXML_BATCH);
        result.setMaxRowsPerPoll(10);
        return result;
    }

    public List<Message<String>> extractBookFixmlItems(List<Map<String, String>> bookFixmlList)
    {
        List<Message<String>> result = new ArrayList<>();
        for (Map<String, String> bookItem : bookFixmlList)
        {
            Map<String, Object> headers = new HashMap<>();
            headers.put("FixmlId", bookItem.get("FixmlId"));
            GenericMessage<String> bookMessage = new GenericMessage<String>(bookItem.get("Value"), headers);
            result.add(bookMessage);
        }
        return result;
    }

    @Bean
    public MessageHandler fileWritingMessageHandler()
        throws IOException
    {
        Path bookFixmlTmpStorageFolderPath = Paths.get(bookFixmlStorageFolder);
        if (!Files.exists(bookFixmlTmpStorageFolderPath))
        {
            Files.createDirectories(bookFixmlTmpStorageFolderPath);
        }

        FileWritingMessageHandler handler = new FileWritingMessageHandler(bookFixmlTmpStorageFolderPath.toFile());
        handler.setFileExistsMode(FileExistsMode.IGNORE);

        DefaultFileNameGenerator fixmlNameGenerator = new DefaultFileNameGenerator();
        fixmlNameGenerator.setExpression("headers['FixmlId']+'.fixml'");
        handler.setFileNameGenerator(fixmlNameGenerator);

        return handler;
    }

    @Bean
    public MessageChannel bookFixmlFileOutputChannel()
    {
        return new PublishSubscribeChannel();
    }

    @Bean
    public MessageHandler bookFxmlBooferOutboundGateway()
    {

        JdbcOutboundGateway result = new JdbcOutboundGateway(bookFxmlBufferDataSource,
                QUERY_UPDATE_BOOK_FIXML_BATCH_STATUS);
        result.setOutputChannelName("nullChannel");

        ExpressionEvaluatingSqlParameterSourceFactory sqlParameterSourceFactory = new ExpressionEvaluatingSqlParameterSourceFactory();
        result.setRequestSqlParameterSourceFactory(sqlParameterSourceFactory);

        Map<String, String> parameterExpressions = new HashMap<>();
        parameterExpressions.put("FixmlId", "headers['FixmlId']");

        sqlParameterSourceFactory.setParameterExpressions(parameterExpressions);

        return result;
    }

    public JobLaunchRequest bookFixmlMessageToBookJobRequest(File fixmlFile)
    {
        JobParametersBuilder jpb = new JobParametersBuilder();
        jpb.addString("xmlFilePath",
                      fixmlFile.getAbsolutePath());
        jpb.addDate("date",
                    new Date());

        JobLaunchRequest result = new JobLaunchRequest(parseBookFxmlJob,
                jpb.toJobParameters());
        return result;
    }

    @Bean
    public MessageHandler bookProcessingGateway()
    {
        JobLaunchingGateway result = new JobLaunchingGateway(jobLauncher);
        result.setOutputChannelName("nullChannel");
        return result;
    }

    public void bookFixmlFileDelete(Message<?> msg)
        throws MessagingException
    {
        JobExecution jobExecution = (JobExecution) msg.getPayload();
        BatchStatus status = jobExecution.getStatus();
        if (!BatchStatus.COMPLETED.equals(status) && !BatchStatus.FAILED.equals(status))
        {
            return;
        }
        String bookFixmlFilePath = (String) msg.getHeaders().get("xmlFilePath");
        try
        {
            Files.deleteIfExists(Paths.get(bookFixmlFilePath));
        }
        catch (IOException e)
        {
            throw new MessagingException(msg, e);
        }
    }

    @Bean
    public MessageChannel bookFixmlParseCompleateOuputChannel()
    {
        return new DirectChannel();
    }

}
