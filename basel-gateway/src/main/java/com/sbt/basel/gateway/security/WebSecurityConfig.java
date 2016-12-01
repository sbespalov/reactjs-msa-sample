package com.sbt.basel.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
            @Qualifier("userDetailsAuthenticationProvider") AuthenticationProvider userDetailsAuthenticationProvider,
            @Qualifier("jwtAuthenticationProvider") AuthenticationProvider jwtAuthenticationProvider) throws Exception
    {
        auth.authenticationProvider(userDetailsAuthenticationProvider).authenticationProvider(jwtAuthenticationProvider)
                .eraseCredentials(false);

    }

    @Configuration
    @Order(1)
    public static class BasicSecurityConfig extends WebSecurityConfigurerAdapter
    {

        @Override
        protected void configure(HttpSecurity http) throws Exception
        {
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .antMatcher("/authenticate").authorizeRequests().antMatchers("/authenticate").authenticated().and()
                    .httpBasic();
        }
    }

    @Configuration
    @Order(2)
    public static class JwtSecurityConfig extends WebSecurityConfigurerAdapter
    {

        @Override
        protected void configure(HttpSecurity http) throws Exception
        {

            JWTAuthenticationFilter jwtFilter = new JWTAuthenticationFilter(authenticationManager());

            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                    .antMatchers("/", "/*.js", "/*.css", "/logout").permitAll().anyRequest().authenticated().and()
                    .addFilterAfter(jwtFilter, BasicAuthenticationFilter.class).logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and()
                    .exceptionHandling().authenticationEntryPoint(new Http401AuthenticationEntryPoint("Bearer")).and()
                    .csrf().disable();

        }

    }

    @Bean
    public AuthenticationProvider userDetailsAuthenticationProvider(UserDetailsService userDetailsService)
    {
        DaoAuthenticationProvider result = new DaoAuthenticationProvider();
        result.setUserDetailsService(userDetailsService);
        return result;
    }

    @Bean
    public AuthenticationProvider jwtAuthenticationProvider()
    {
        return new JWtAuthenticationProvider();
    }
}
