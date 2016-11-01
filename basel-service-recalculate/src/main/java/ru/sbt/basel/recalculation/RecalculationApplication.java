package ru.sbt.basel.recalculation;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@SpringBootApplication
// @EnableResourceServer
// @EnableDiscoveryClient
// @EnableOAuth2Client
// @EnableFeignClients
// @EnableGlobalMethodSecurity(prePostEnabled = true)
// @EnableConfigurationProperties
// @Configuration
public class RecalculationApplication {

	// @Autowired
	// private ResourceServerProperties sso;

	public static void main(String[] args) {
		SpringApplication.run(RecalculationApplication.class, args);
	}

	@Bean
	public MappingJackson2HttpMessageConverter jacksonHttpMessageConverter() {
		return new MappingJackson2HttpMessageConverter();
	}

	@Bean
	public FormHttpMessageConverter formHttpMessageConverter() {
		return new FormHttpMessageConverter();
	}

	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter(
			MappingJackson2HttpMessageConverter jacksonHttpMessageConverter,
			FormHttpMessageConverter formHttpMessageConverter) {
		RequestMappingHandlerAdapter result = new RequestMappingHandlerAdapter();
		result.setMessageConverters(
				Arrays.asList(new HttpMessageConverter[] { jacksonHttpMessageConverter }));
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
