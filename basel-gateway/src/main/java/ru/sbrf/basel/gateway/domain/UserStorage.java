package ru.sbrf.basel.gateway.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user-storage")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class UserStorage
{

    private List<User> userList = new ArrayList<>();

    @XmlElement(name = "user")
    public List<User> getUserList()
    {
        return userList;
    }

    public void setUserList(List<User> userList)
    {
        this.userList = userList;
    }

}
