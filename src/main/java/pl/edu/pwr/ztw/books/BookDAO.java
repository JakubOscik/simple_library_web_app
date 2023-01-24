package pl.edu.pwr.ztw.books;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pwr.ztw.books.Author;
import pl.edu.pwr.ztw.books.Book;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface BookDAO extends CrudRepository<Book, Integer> {
    @Transactional
    @Modifying
    @Query("update Book b set b.title = ?1, b.author = ?2, b.pages = ?3 where b.id = ?4")
    int updateTitleAndAuthorAndPagesById(String title, Author author, int pages, int id);
}