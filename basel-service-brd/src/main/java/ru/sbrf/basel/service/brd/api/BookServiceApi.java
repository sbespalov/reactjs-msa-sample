package ru.sbrf.basel.service.brd.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.sbrf.basel.service.brd.api.dto.BslBookCreateRequest;

@RequestMapping("/book")
public interface BookServiceApi
{

    @RequestMapping(method = RequestMethod.PUT)
    public void create(@RequestBody BslBookCreateRequest bslBookCreateRequest);

}
