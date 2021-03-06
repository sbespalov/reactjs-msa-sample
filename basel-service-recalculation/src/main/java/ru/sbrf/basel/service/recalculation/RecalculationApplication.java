package ru.sbrf.basel.service.recalculation;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@SpringBootApplication
// @EnableResourceServer
@EnableDiscoveryClient
// @EnableOAuth2Client
@EnableFeignClients
// @EnableGlobalMethodSecurity(prePostEnabled = true)
// @EnableConfigurationProperties
@Configuration
public class RecalculationApplication
{

    // @Autowired
    // private ResourceServerProperties sso;

    public static void main(String[] args)
    {
        SpringApplication.run(RecalculationApplication.class, args);
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

    // @Bean
    // @ConfigurationProperties(prefix = "security.oauth2.client")
    // public ClientCredentialsResourceDetails
    // clientCredentialsResourceDetails() {
    // return new ClientCredentialsResourceDetails();
    // }

    // @Bean
    // public RequestInterceptor oauth2FeignRequestInterceptor(){
    // return new OAuth2FeignRequestInterceptor(new
    // DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
    // }

    // @Bean
    // public OAuth2RestTemplate clientCredentialsRestTemplate() {
    // return new OAuth2RestTemplate(clientCredentialsResourceDetails());
    // }

    // @Bean
    // public ResourceServerTokenServices tokenServices() {
    // return new CustomUserInfoTokenServices(sso.getUserInfoUri(),
    // sso.getClientId());
    // }
    //
    // @Override
    // public void configure(HttpSecurity http) throws Exception {
    // http.authorizeRequests()
    // .antMatchers("/" , "/demo").permitAll()
    // .anyRequest().authenticated();
    // }
}
