package ru.sbrf.basel.integration.gateway.brd.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.AbstractJob;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.AbstractJobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.core.scope.StepScope;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.oxm.Unmarshaller;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import ru.trd.msk.svn.xsd.fixml.Book;

@Configuration
@EnableIntegration
@ComponentScan(basePackageClasses = BrdBatchConfiguration.class)
public class BrdBatchConfiguration
{

    @Bean
    @DependsOn("bookItemReader")
    public Step parseBookFxmlStep(StepBuilderFactory stepBuilderFactory,
                                  @Qualifier("bookItemReader") ItemStreamReader<Book> bookStaxItemReader,
                                  BookBatchWriter batchWriter)
    {
        return stepBuilderFactory.get("parseBookFxmlStep")
                                 .<Book, Book>chunk(100)
                                 .reader(bookStaxItemReader)
                                 .writer(batchWriter)
                                 .build();
    }

    @Bean
    @org.springframework.batch.core.configuration.annotation.StepScope
    public ItemStreamReader<Book> bookItemReader(@Value("#{jobParameters['xmlFilePath']}") String bookFixmlFilePath,
                                                 @Qualifier("bookFixmlMarshaller") Unmarshaller unmarshaller)
    {
        StaxEventItemReader<Book> result = new StaxEventItemReader<Book>();
        result.setFragmentRootElementName("Book");
        result.setResource(new FileSystemResource(bookFixmlFilePath));
        result.setUnmarshaller(unmarshaller);
        return result;
    }

    @Bean
    public Job parseBookFxmlJob(JobBuilderFactory jobBuilderFactory,
                                @Qualifier("parseBookFxmlStep") Step parseBookFxmlStep,
                                FixmlJobExecutionListenerAdapter listener)
        throws Exception
    {
        Job result = jobBuilderFactory.get("parseBookFxmlJob")
                                      .incrementer(new RunIdIncrementer())
                                      .start(parseBookFxmlStep)
                                      .build();
        ((AbstractJob) result).registerJobExecutionListener(new JobExecutionListenerSupport()
        {

            @Override
            public void afterJob(JobExecution jobExecution)
            {
                listener.bookFixmlParseJobCompleate(jobExecution);
            }

        });
        return result;
    }

    @Bean
    public StepScope stepScope()
    {
        StepScope stepScope = new StepScope();
        stepScope.setAutoProxy(true);
        return stepScope;
    }

    @Bean("jobRepository")
    public FactoryBean<JobRepository> jobRepositoryFactoryBean()
    {
        return new MapJobRepositoryFactoryBean();
    }

    @Bean
    public PlatformTransactionManager jobTransactionManager()
    {
        return ((AbstractJobRepositoryFactoryBean) jobRepositoryFactoryBean()).getTransactionManager();
    }

    @Bean
    public StepBuilderFactory stepBuilderFactory(JobRepository jobRepository)
    {
        return new StepBuilderFactory(jobRepository, jobTransactionManager());
    };

    @Bean
    public JobBuilderFactory jobBuilderFactory(JobRepository jobRepository)
    {
        return new JobBuilderFactory(jobRepository);
    }

    @Bean
    public TaskExecutor jobTaskExecutor()
    {
        return new ThreadPoolTaskExecutor();
    }

    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepository)
    {
        SimpleJobLauncher result = new SimpleJobLauncher();
        result.setJobRepository(jobRepository);
        result.setTaskExecutor(jobTaskExecutor());
        return result;
    }

}
