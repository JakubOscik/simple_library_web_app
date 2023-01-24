package pl.edu.pwr.ztw.books;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class AuthorService implements IAuthorService {
    private final AuthorDAO authorDAO;
    private final ModelMapper modelMapper = new ModelMapper();

    public AuthorService(AuthorDAO bookDAO) {
        this.authorDAO = bookDAO;
    }

    @Override
    public Collection<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();
        authorDAO.findAll().iterator().forEachRemaining(authors::add);
        return authors;
    }

    @Override
    public Author getAuthor(int id) {
        return authorDAO.findById(id).orElse(null);
    }

    @Override
    public void deleteAuthor(int id) {
        authorDAO.deleteById(id);
    }

    @Override
    public AuthorDTO createAuthor(AuthorCreateDTO author) {
        Author newAuthor = modelMapper.map(author, Author.class);
        Author createdAuthor = authorDAO.save(newAuthor);
        return modelMapper.map(createdAuthor, AuthorDTO.class);
    }

    @Override
    public boolean updateAuthor(int id, AuthorDTO author) {
        if(id != author.getId()){
            return false;
        }
        return authorDAO.updateNameAndSurnameById(author.getName(), author.getSurname(), id) > 0;
    }

    public boolean checkIfAuthorExists(int id) {
        return authorDAO.findById(id).isPresent();
    }

    @Override
    public Collection<BookNoAuthorDTO> getBooks(int authorId) {
        Author author = authorDAO.findById(authorId).orElse(null);
        if (author == null) return null;
        return author.getBooks().stream().map(b -> modelMapper.map(b , BookNoAuthorDTO.class)).collect(Collectors.toList());
    }
}