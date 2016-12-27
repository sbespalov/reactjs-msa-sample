package ru.sbrf.basel.service.brd;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@SpringBootApplication
@EnableDiscoveryClient
@Configuration
public class BrdApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(BrdApplication.class, args);
    }

    @Bean
    public MappingJackson2HttpMessageConverter jacksonHttpMessageConverter()
    {
        MappingJackson2HttpMessageConverter jmc = new MappingJackson2HttpMessageConverter();
        jmc.getObjectMapper().setDateFormat(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
        return jmc;
    }

    @Bean
    public FormHttpMessageConverter formHttpMessageConverter()
    {
        return new FormHttpMessageConverter();
    }

    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(
                                                                     MappingJackson2HttpMessageConverter jacksonHttpMessageConverter,
                                                                     FormHttpMessageConverter formHttpMessageConverter)
    {
        RequestMappingHandlerAdapter result = new RequestMappingHandlerAdapter();
        result.setMessageConverters(Arrays.asList(new HttpMessageConverter[] { jacksonHttpMessageConverter }));
        return result;
    }

}
