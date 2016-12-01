package com.sbt.basel.gateway.security.api.dto;

public class BslAuthenticateResponse
{

    private String jwt;

    private String firstName;

    private String lastName;

    private String email;

    public String getJwt()
    {
        return jwt;
    }

    public void setJwt(String jwt)
    {
        this.jwt = jwt;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String userName)
    {
        this.firstName = userName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

}
