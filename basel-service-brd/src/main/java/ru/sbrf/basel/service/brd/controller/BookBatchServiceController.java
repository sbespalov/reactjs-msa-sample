package ru.sbrf.basel.service.brd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.sbrf.basel.service.brd.api.BookBatchServiceApi;
import ru.sbrf.basel.service.brd.api.dto.BslBook;
import ru.sbrf.basel.service.brd.api.dto.BslBookProcessBatchRequest;
import ru.sbrf.basel.service.brd.domain.Book;
import ru.sbrf.basel.service.brd.repository.BookRepository;

@RestController
public class BookBatchServiceController implements BookBatchServiceApi
{

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional
    public void process(@RequestBody BslBookProcessBatchRequest bslBookCreateRequest)
    {
        for (BslBook bslBook : bslBookCreateRequest.getBookList())
        {
            Book bookEntity = bookRepository.find(bslBook.getBookCode(), bslBook.getSourceSystem());
            if (bookEntity == null)
            {
                bookEntity = new Book();
            }

            bookEntity.setBookCode(bslBook.getBookCode());
            bookEntity.setBookType(bslBook.getBookType());
            bookEntity.setSourceSystem(bslBook.getSourceSystem());
            bookRepository.save(bookEntity);
        }

    }

}
