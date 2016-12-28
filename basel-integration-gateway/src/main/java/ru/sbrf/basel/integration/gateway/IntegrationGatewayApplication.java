package ru.sbrf.basel.integration.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.integration.config.EnableIntegration;

import ru.sbrf.basel.integration.gateway.brd.BrdConfiguration;

@SpringBootApplication(exclude = { BatchAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
                                   JpaRepositoriesAutoConfiguration.class })
@EnableDiscoveryClient
@EnableFeignClients
@EnableIntegration
@Import({ BrdConfiguration.class })
public class IntegrationGatewayApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(IntegrationGatewayApplication.class, args);

    }

}
