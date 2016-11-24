package com.sbt.basel.gateway.security;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sbt.basel.gateway.domain.UserStorage;

@Component
public class XMlUserDetailsService implements UserDetailsService
{

    private UserStorage userStorate = new UserStorage();

    @PostConstruct
    public
           void init() throws Exception
    {
        JAXBContext jaxbContext = JAXBContext.newInstance(UserStorage.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        userStorate = (UserStorage) jaxbUnmarshaller.unmarshal(getClass().getResourceAsStream("user-storage.xml"));
    }

    @Override
    public
           UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserDetails result = userStorate.getUserList().stream().filter((user) -> {
            return user.getName().equals(username);
        }).map((user) -> {
            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                    Arrays.asList(new GrantedAuthority[] {}));
        }).findFirst().orElseThrow(
                () -> new UsernameNotFoundException(String.format("User not found: userName-[%s]", username)));

        return result;

    }

}
