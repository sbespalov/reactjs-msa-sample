package ru.sbrf.basel.service.brd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import ru.sbrf.basel.service.brd.api.BookServiceApi;
import ru.sbrf.basel.service.brd.api.dto.BslBook;
import ru.sbrf.basel.service.brd.api.dto.BslFindBookListRequest;
import ru.sbrf.basel.service.brd.api.dto.BslFindBookListResponse;
import ru.sbrf.basel.service.brd.domain.Book;
import ru.sbrf.basel.service.brd.repository.BookRepository;

@RestController
public class BookServiceController implements BookServiceApi
{

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    @Override
    public BslFindBookListResponse findBookListRequest(BslFindBookListRequest bslFindBookListRequest)
    {
        BslFindBookListResponse result = new BslFindBookListResponse();

        List<Book> bookList = bookRepository.findPage(bslFindBookListRequest.getBookCode(),
                                                      bslFindBookListRequest.getBookType(),
                                                      bslFindBookListRequest.getSourceSystem());
        result.getPageResponse().setPageSize(bookList.size());
        result.getPageResponse().setPageNumber(bslFindBookListRequest.getPageRequest().getPageNumber());

        for (Book book : bookList)
        {
            BslBook item = new BslBook();
            result.getBookList().add(item);

            item.setCode(book.getBookCode());
            item.setType(book.getBookType());
            item.setSourceSystem(book.getSourceSystem());
        }
        return result;
    }

}
