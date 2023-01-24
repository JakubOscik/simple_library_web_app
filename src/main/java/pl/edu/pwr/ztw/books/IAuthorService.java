package pl.edu.pwr.ztw.books;

import java.util.Collection;

public interface IAuthorService {
    Collection<Author> getAuthors();
    Author getAuthor(int id);
    void deleteAuthor(int id);
    AuthorDTO createAuthor(AuthorCreateDTO author);
    boolean updateAuthor(int id, AuthorDTO author);
    boolean checkIfAuthorExists(int id);
    Collection<BookNoAuthorDTO> getBooks(int authorId);
}