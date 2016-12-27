package ru.sbrf.basel.integration.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.integration.config.EnableIntegration;

import ru.sbrf.basel.integration.gateway.brd.BrdConfiguration;
import ru.sbrf.basel.integration.gateway.client.BaselClientRegistry.BookBatchServiceApiClient;
import ru.sbrf.basel.service.brd.api.dto.BslBookProcessBatchRequest;

@SpringBootApplication(exclude = BatchAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
@EnableIntegration
@Import({ BrdConfiguration.class })
public class IntegrationGatewayApplication
{

    public static void main(String[] args)
    {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(IntegrationGatewayApplication.class,
                                                                                  args);
        test(applicationContext.getBean(BookBatchServiceApiClient.class));

    }

    public static void test(BookBatchServiceApiClient client)
    {
        try
        {
            client.process(new BslBookProcessBatchRequest());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
