package pl.edu.pwr.ztw.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class AuthorController {

    @Autowired
    IAuthorService authorsService;

    @RequestMapping(value = "/get/authors", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthors(){
        return new ResponseEntity<>(authorsService.getAuthors(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/author/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBook(@PathVariable("id") int id){
        return new ResponseEntity<>(authorsService.getAuthor(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/author/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBook(@PathVariable("id") int id) {
        authorsService.deleteAuthor(id);
        return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/create/author", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody AuthorCreateDTO author) {
        return new ResponseEntity<>(authorsService.createAuthor(author), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/author/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateBook(@PathVariable("id") int id, @RequestBody AuthorDTO author) {
        if(authorsService.checkIfAuthorExists(id)) {
            if (authorsService.updateAuthor(id, author))
                return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
            else
                return new ResponseEntity<>("Incorrect data", HttpStatus.BAD_REQUEST);
        }
        else
            return new ResponseEntity<>("Author not found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/getbooks/author/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBooks(@PathVariable("id") int id){
        Collection<BookNoAuthorDTO> books = authorsService.getBooks(id);
        if(books == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
