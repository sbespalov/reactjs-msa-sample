package ru.sbrf.basel.gateway.security.api;

import ru.sbrf.basel.gateway.security.api.dto.BslAuthenticateResponse;

public interface AuthenticationApi
{

    public BslAuthenticateResponse authenticate() throws Exception;

}
