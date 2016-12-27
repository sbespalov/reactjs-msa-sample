package ru.sbrf.basel.integration.gateway.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import ru.sbrf.basel.service.brd.api.BookBatchServiceApi;

public interface BaselClientRegistry
{

    @FeignClient(name = "basel-service-brd")
    public static interface BookBatchServiceApiClient extends BookBatchServiceApi
    {

    }

}
