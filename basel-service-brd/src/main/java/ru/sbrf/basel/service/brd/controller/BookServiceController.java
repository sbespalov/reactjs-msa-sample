package ru.sbrf.basel.service.brd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Joiner;

import ru.sbrf.basel.service.api.common.dto.FindPageResponseDto;
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

        Page<Book> bookPage = bookRepository.findPage(Joiner.on("")
                                                            .skipNulls()
                                                            .join(bslFindBookListRequest.getBookCode(), "%"),
                                                      Joiner.on("")
                                                            .skipNulls()
                                                            .join(bslFindBookListRequest.getBookType(), "%"),
                                                      Joiner.on("")
                                                            .skipNulls()
                                                            .join(bslFindBookListRequest.getSourceSystem(), "%"),
                                                      new PageRequest(
                                                              bslFindBookListRequest.getPageRequest().getPageNumber() -
                                                                      1,
                                                              bslFindBookListRequest.getPageRequest().getPageSize()));
        Page<BslBook> bslBookPage = bookPage.map((book) -> {
            BslBook item = new BslBook();
            item.setBookCode(book.getBookCode());
            item.setBookType(book.getBookType());
            item.setSourceSystem(book.getSourceSystem());

            return item;
        });
        result.setPageResponse(new FindPageResponseDto(bslBookPage.getNumber() + 1, bslBookPage.getSize(),
                Long.valueOf(bslBookPage.getNumberOfElements())));
        result.setBookList(bslBookPage.getContent());
        return result;
    }

}
