package ru.sbrf.basel.service.brd.api.dto;

import java.util.ArrayList;
import java.util.List;

import ru.sbrf.basel.service.api.common.dto.FindPageResponseDto;

public class BslFindBookListResponse
{

    private FindPageResponseDto pageResponse = new FindPageResponseDto()
    {

        @Override
        public Integer getPageNumber()
        {
            return 1;
        }

        @Override
        public Integer getPageSize()
        {
            return bookList.size();
        }

        @Override
        public Long getTotalCount()
        {
            return Long.valueOf(bookList.size());
        }

    };

    private List<BslBook> bookList = new ArrayList<>();

    public FindPageResponseDto getPageResponse()
    {
        return pageResponse;
    }

    public void setPageResponse(FindPageResponseDto pageResponse)
    {
        this.pageResponse = pageResponse;
    }

    public List<BslBook> getBookList()
    {
        return bookList;
    }

    public void setBookList(List<BslBook> bookList)
    {
        this.bookList = bookList;
    }

}
