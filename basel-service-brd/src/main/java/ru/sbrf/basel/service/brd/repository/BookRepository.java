package ru.sbrf.basel.service.brd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.sbrf.basel.service.brd.domain.Book;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long>
{

    @Query("select e from Book e where e.bookCode = :bookCode and e.sourceSystem = :sourceSystem")
    Book find(@Param("bookCode") String bookCode,
              @Param("sourceSystem") String sourceSystem);

    @Query(" select     " +
            "   e       " +
            "from       " +
            "   Book e  " +
            "where (nullif(:bookCode,'%') is null or e.bookCode like :bookCode)                 " +
            "       and (nullif(:bookType,'%') is null or e.bookType like :bookType)           " +
            "       and (nullif(:sourceSystem,'%') is null or e.sourceSystem like :sourceSystem)")
    Page<Book> findPage(@Param("bookCode") String bookCode,
                        @Param("bookType") String bookType,
                        @Param("sourceSystem") String sourceSystem,
                        Pageable pageable);

}
