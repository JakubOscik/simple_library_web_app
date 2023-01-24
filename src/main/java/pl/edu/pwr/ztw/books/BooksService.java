package pl.edu.pwr.ztw.books;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class BooksService implements IBooksService {
    private final BookDAO     bookDAO;
    private final AuthorDAO authorDAO;
    private final ModelMapper modelMapper = new ModelMapper();

    public BooksService(BookDAO bookDAO, AuthorDAO authorDAO) {
        this.bookDAO = bookDAO;
        this.authorDAO = authorDAO;
    }

    @Override
    public Collection<Book> getBooks() {
        List<Book>  books = new ArrayList<>();
        bookDAO.findAll().iterator().forEachRemaining(books::add);
        return books;
    }

    @Override
    public Book getBook(int id) {
        return bookDAO.findById(id).orElse(null);
    }

    @Override
    public void deleteBook(int id) {
        bookDAO.deleteById(id);
    }

    @Override
    public Book createBook(BookCreateDTO book) {
        Book             newBook = modelMapper.map(book, Book.class);
        Optional<Author> author  = authorDAO.findById(newBook.getAuthor().getId());
        newBook.setAuthor(author.orElse(null));
        return bookDAO.save(newBook);
    }

    @Override
    public boolean updateBook(int id, BookDTO book) {
        if(id != book.getId()){
            return false;
        }
        Author author = null;
        if(book.getAuthor() != null) {
            author = authorDAO.findById(book.getAuthor()).orElse(null);
            if(author == null) return false;
        }
        return bookDAO.updateTitleAndAuthorAndPagesById(book.getTitle(), author, book.getPages(), id) > 0;
    }

    public boolean checkIfBookExists(int id) {
        return bookDAO.findById(id).isPresent();
    }
}