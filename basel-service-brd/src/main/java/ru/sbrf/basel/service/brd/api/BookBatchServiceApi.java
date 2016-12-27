package ru.sbrf.basel.service.brd.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.sbrf.basel.service.brd.api.dto.BslBookProcessBatchRequest;

@RequestMapping("/book/batch")
public interface BookBatchServiceApi
{

    @RequestMapping(method = RequestMethod.PUT)
    public void process(@RequestBody BslBookProcessBatchRequest bslBookCreateRequest);

}
