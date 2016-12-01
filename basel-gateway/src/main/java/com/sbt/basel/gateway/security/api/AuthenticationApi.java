package com.sbt.basel.gateway.security.api;

import com.sbt.basel.gateway.security.api.dto.BslAuthenticateResponse;

public interface AuthenticationApi
{

    public BslAuthenticateResponse authenticate() throws Exception;

}
