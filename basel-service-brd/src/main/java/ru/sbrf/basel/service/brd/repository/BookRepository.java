package ru.sbrf.basel.service.brd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.sbrf.basel.service.brd.domain.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>
{

    @Query("select e from Book e where e.bookCode = :bookCode and e.sourceSystem = :sourceSystem")
    Book find(@Param("bookCode") String bookCode,
              @Param("sourceSystem") String sourceSystem);

    @Query("select e from Book e where (e.bookCode = :bookCode or :bookCode is null) and (e.bookType = :bookType or :bookType is null) and (e.sourceSystem = :sourceSystem or :sourceSystem is null)")
    List<Book> findPage(@Param("bookCode") String bookCode,
                        @Param("bookType") String bookType,
                        @Param("sourceSystem") String sourceSystem);

}
