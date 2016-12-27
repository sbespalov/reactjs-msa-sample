package ru.sbrf.basel.service.brd.controller;

import org.springframework.web.bind.annotation.RestController;

import ru.sbrf.basel.service.brd.api.BookBatchServiceApi;
import ru.sbrf.basel.service.brd.api.dto.BslBookProcessBatchRequest;

@RestController
public class BookBatchServiceController implements BookBatchServiceApi
{

    @Override
    public void process(BslBookProcessBatchRequest bslBookCreateRequest)
    {
        System.out.println(bslBookCreateRequest.getBookList().size());
    }

}
