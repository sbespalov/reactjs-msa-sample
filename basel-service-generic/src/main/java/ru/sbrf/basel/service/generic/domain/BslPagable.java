package ru.sbrf.basel.service.generic.domain;

import org.springframework.data.domain.PageRequest;

import ru.sbrf.basel.service.api.common.dto.FindPageRequestDto;

public class BslPagable extends PageRequest
{

    public BslPagable(FindPageRequestDto dto){
        super(dto.getPageNumber() - 1, dto.getPageSize());
    }
    
}
