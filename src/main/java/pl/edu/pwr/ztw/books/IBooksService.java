package pl.edu.pwr.ztw.books;
import java.util.Collection;

public interface IBooksService {
    Collection<Book> getBooks();
    Book getBook(int id);
    void deleteBook(int id);
    Book createBook(BookCreateDTO book);
    boolean updateBook(int id, BookDTO book);
    boolean checkIfBookExists(int id);

}