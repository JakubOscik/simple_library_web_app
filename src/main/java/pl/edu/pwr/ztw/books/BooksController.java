package pl.edu.pwr.ztw.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BooksController {

    @Autowired
    IBooksService booksService;

    @RequestMapping(value = "/get/books", method = RequestMethod.GET)
    public ResponseEntity<Object> getBooks(){
        return new ResponseEntity<>(booksService.getBooks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBook(@PathVariable("id") int id){
        return new ResponseEntity<>(booksService.getBook(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBook(@PathVariable("id") int id) {
        booksService.deleteBook(id);
        return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/create/book", method = RequestMethod.POST)
    public ResponseEntity<Object> createBook(@RequestBody BookCreateDTO book) {
        return new ResponseEntity<>(booksService.createBook(book), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/book/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateBook(@PathVariable("id") int id, @RequestBody BookDTO book) {
        if(booksService.checkIfBookExists(id)) {
            if (booksService.updateBook(id, book))
                return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
            else
                return new ResponseEntity<>("Incorrect data", HttpStatus.BAD_REQUEST);
        }
        else
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
    }
}
