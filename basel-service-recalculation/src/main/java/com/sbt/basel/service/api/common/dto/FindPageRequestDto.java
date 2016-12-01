package com.sbt.basel.service.api.common.dto;

public class FindPageRequestDto
{

    public static final int DEFAULT_PAGE_SIZE = 20;

    private Integer pageNumber = 1;

    private Integer pageSize = DEFAULT_PAGE_SIZE;

    private Boolean fetchCount = Boolean.FALSE;

    public Integer getPageNumber()
    {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber)
    {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public Boolean getFetchCount()
    {
        return fetchCount;
    }

    public void setFetchCount(Boolean fetchCount)
    {
        this.fetchCount = fetchCount;
    }

}
