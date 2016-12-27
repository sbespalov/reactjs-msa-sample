package ru.sbrf.basel.integration.gateway.brd.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.sbrf.basel.integration.gateway.client.BaselClientRegistry.BookBatchServiceApiClient;
import ru.sbrf.basel.service.brd.api.dto.BslBook;
import ru.sbrf.basel.service.brd.api.dto.BslBookProcessBatchRequest;
import ru.trd.msk.svn.xsd.fixml.Book;

@Component
public class BookBatchWriter implements ItemWriter<Book>
{

    @Autowired(required = true)
    private BookBatchServiceApiClient bookBatchService;

    @Override
    public void write(List<? extends Book> items)
        throws Exception
    {
        BslBookProcessBatchRequest bslBookProcessRequest = new BslBookProcessBatchRequest();
        for (Book bookItem : items)
        {
            BslBook book = new BslBook();
            book.setCode(bookItem.getName());
            book.setSourceSystem(bookItem.getSrcName());
            book.setType(bookItem.getTyp());
            bslBookProcessRequest.getBookList().add(book);
        }

        bookBatchService.process(bslBookProcessRequest);
    }

}
