package ru.sbrf.basel.gateway.security;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ru.sbrf.basel.gateway.domain.UserStorage;

@Component
public class XMlUserDetailsService implements UserDetailsService
{

    private UserStorage userStorate = new UserStorage();

    @PostConstruct
    public void init() throws Exception
    {
        JAXBContext jaxbContext = JAXBContext.newInstance(UserStorage.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        userStorate = (UserStorage) jaxbUnmarshaller.unmarshal(getClass().getResourceAsStream("user-storage.xml"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        UserDetails result = userStorate.getUserList().stream().filter((user) ->
        {
            return user.getEmail().equals(email);
        }).map((user) ->
        {
            return toUser(user);
        }).findFirst().orElseThrow(
                () -> new UsernameNotFoundException(String.format("User not found: userName-[%s]", email)));

        return result;

    }

    private User toUser(ru.sbrf.basel.gateway.domain.User user)
    {
        BslUserDetails result = new BslUserDetails(user.getEmail(), user.getPassword(),
                Arrays.asList(new GrantedAuthority[] {}));
        result.setFirstName(user.getFirstName());
        result.setLastName(user.getLastName());
        return result;
    }

}
