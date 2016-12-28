package ru.sbrf.basel.service.brd.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.sbrf.basel.service.brd.api.dto.BslFindBookListRequest;
import ru.sbrf.basel.service.brd.api.dto.BslFindBookListResponse;

@RequestMapping("/book")
public interface BookServiceApi
{

    @RequestMapping(path = "/find", method = RequestMethod.GET)
    public BslFindBookListResponse findBookListRequest(BslFindBookListRequest bslFindBookListRequest);

}
