package ru.sbrf.basel.service.brd.api.dto;

import java.util.ArrayList;
import java.util.List;

public class BslBookProcessBatchRequest
{

    private List<BslBook> bookList = new ArrayList<>();

    public List<BslBook> getBookList()
    {
        return bookList;
    }

    public void setBookList(List<BslBook> bookList)
    {
        this.bookList = bookList;
    }

}
