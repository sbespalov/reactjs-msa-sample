package com.sbt.basel.gateway.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class User
{

    private String name;
    private String password;

    public
           String getName()
    {
        return name;
    }

    public
           void setName(String name)
    {
        this.name = name;
    }

    public
           String getPassword()
    {
        return password;
    }

    public
           void setPassword(String password)
    {
        this.password = password;
    }

}
