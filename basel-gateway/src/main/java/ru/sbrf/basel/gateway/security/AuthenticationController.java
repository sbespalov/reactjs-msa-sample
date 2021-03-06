package ru.sbrf.basel.gateway.security;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Arrays;
import java.util.List;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.sbrf.basel.gateway.security.api.AuthenticationApi;
import ru.sbrf.basel.gateway.security.api.dto.BslAuthenticateResponse;

@RestController
public class AuthenticationController implements AuthenticationApi
{

    private Key key;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void init(@Value("${basel.security.jwtSecret}") String secret)
    {

        try
        {
            key = new HmacKey(secret.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException e1)
        {

        }
    }

    @RequestMapping("/authenticate")
    public BslAuthenticateResponse authenticate() throws Exception
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BslUserDetails user = (BslUserDetails) authentication.getPrincipal();
        String credentials = (String) authentication.getCredentials();

        JwtClaims claims = new JwtClaims();
        claims.setIssuer("Basel"); // who creates the token and signs it
        // claims.setExpirationTimeMinutesInTheFuture(10); // time when the
        // token
        // // will expire (10
        // // minutes from now)
        claims.setGeneratedJwtId(); // a unique identifier for the token
        claims.setIssuedAtToNow(); // when the token was issued/created (now)
        // claims.setNotBeforeMinutesInThePast(2); // time before which the
        // token
        // // is not yet valid (2 minutes
        // // ago)
        claims.setSubject(user.getUsername()); // the subject/principal is whom
                                               // the token
        // is about
        claims.setClaim("credentials", credentials); // additional
                                                     // claims/attributes about
                                                     // the subject can be
                                                     // added
        List<String> groups = Arrays.asList("group-one", "other-group", "group-three");
        claims.setStringListClaim("groups", groups); // multi-valued claims work
                                                     // too and will end up as a
                                                     // JSON array

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(key);
        jws.setDoKeyValidation(false);
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);

        BslAuthenticateResponse result = new BslAuthenticateResponse();
        result.setJwt(jws.getCompactSerialization());
        result.setFirstName(user.getFirstName());
        result.setLastName(user.getLastName());
        result.setEmail(user.getEmail());

        return result;
    }

}
