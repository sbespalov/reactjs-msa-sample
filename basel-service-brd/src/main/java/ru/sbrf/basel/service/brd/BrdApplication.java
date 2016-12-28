package ru.sbrf.basel.service.brd;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public class BrdApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(BrdApplication.class, args);
    }

    @Bean
    public DataSource bookFxmlBufferDataSource(@Value("${basel.service.brd.jdbcUrl}") String jdbcUrl,
                                               @Value("${basel.service.brd.jdbcUser}") String jdbcUser,
                                               @Value("${basel.service.brd.jdbcPassword}") String jdbcPassword)
    {
        ComboPooledDataSource result = new ComboPooledDataSource();
        result.setJdbcUrl(jdbcUrl);
        result.setUser(jdbcUser);
        result.setPassword(jdbcPassword);
        return result;
    }

}
