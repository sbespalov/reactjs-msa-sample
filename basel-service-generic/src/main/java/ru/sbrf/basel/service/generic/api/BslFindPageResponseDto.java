package ru.sbrf.basel.service.generic.api;

import org.springframework.data.domain.Page;

import ru.sbrf.basel.service.api.common.dto.FindPageResponseDto;

public class BslFindPageResponseDto<T> extends FindPageResponseDto
{

    public BslFindPageResponseDto(Page<T> page)
    {
        super(page.getNumber() + 1, page.getSize(), page.getTotalElements());
    }

}
