package com.sbt.basel.gateway.security;

import java.io.UnsupportedEncodingException;
import java.security.Key;

import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class JWtAuthenticationProvider implements AuthenticationProvider
{

    private Key key;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void init(@Value("${basel.security.jwtSecret}") String secret) throws UnsupportedEncodingException
    {

        key = new HmacKey(secret.getBytes("UTF-8"));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {

        JWTAuthentication jwtAuthentication = (JWTAuthentication) authentication;
        String token = jwtAuthentication.getToken();

        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                // .setRequireExpirationTime()
                // .setMaxFutureValidityInMinutes(300)
                .setRequireSubject().setVerificationKey(key).setRelaxVerificationKeyValidation().build();

        JwtClaims jwtClaims;
        String userName;
        try
        {
            jwtClaims = jwtConsumer.processToClaims(token);
            userName = jwtClaims.getSubject();
        }
        catch (InvalidJwtException | MalformedClaimException e)
        {
            e.printStackTrace();
            throw new JwtAuthenticationException(String.format("Invalid JWT: value-[%s]", token), e);
        }

        UserDetails user = userDetailsService.loadUserByUsername(userName);
        Object credentials = jwtClaims.getClaimValue("credentials");
        if (!user.getPassword().equals(credentials))
        {
            throw new BadCredentialsException(
                    String.format(String.format("Credentials don't match: user-[%s] ", userName)));
        }

        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return JWTAuthentication.class.isAssignableFrom(authentication);
    }

    public static class JwtAuthenticationException extends AuthenticationException
    {

        public JwtAuthenticationException(String msg, Throwable t)
        {
            super(msg, t);
        }

        public JwtAuthenticationException(String msg)
        {
            super(msg);
        }

    }
}
