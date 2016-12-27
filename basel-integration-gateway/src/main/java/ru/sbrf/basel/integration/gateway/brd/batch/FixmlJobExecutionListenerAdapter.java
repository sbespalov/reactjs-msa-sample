package ru.sbrf.basel.integration.gateway.brd.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.integration.annotation.Publisher;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class FixmlJobExecutionListenerAdapter
{

    @Publisher(channel = "bookFixmlParseCompleateOuputChannel")
    public Message<JobExecution> bookFixmlParseJobCompleate(JobExecution jobExecution)
    {
        return MessageBuilder.withPayload(jobExecution)
                             .setHeader("xmlFilePath", jobExecution.getJobParameters().getString("xmlFilePath"))
                             .build();
    }

}
