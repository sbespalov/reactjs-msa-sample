package ru.sbrf.basel.service.brd.api.dto;

import ru.sbrf.basel.service.api.common.dto.FindPageRequestDto;

public class BslFindBookListRequest
{

    private String bookCode;
    private String bookType;
    private String sourceSystem;
    private FindPageRequestDto pageRequest = new FindPageRequestDto();

    public String getBookCode()
    {
        return bookCode;
    }

    public void setBookCode(String bookCode)
    {
        this.bookCode = bookCode;
    }

    public String getBookType()
    {
        return bookType;
    }

    public void setBookType(String bookType)
    {
        this.bookType = bookType;
    }

    public String getSourceSystem()
    {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem)
    {
        this.sourceSystem = sourceSystem;
    }

    public FindPageRequestDto getPageRequest()
    {
        return pageRequest;
    }

    public void setPageRequest(FindPageRequestDto pageRequest)
    {
        this.pageRequest = pageRequest;
    }

}
