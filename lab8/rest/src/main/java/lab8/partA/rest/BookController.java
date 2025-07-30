package lab8.partA.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookEntity> addBook(@RequestBody BookEntity book) {
        Optional<BookEntity> bookOptional = bookService.getBook(book.getIsbn());

        if (bookOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BookEntity> updateBook(@RequestBody BookEntity book, @PathVariable Long id) {
        Optional<BookEntity> bookOptional = bookService.getBookById(id);
        if (bookOptional.isPresent()) {
            book.setId(id);
            return new ResponseEntity<>(bookService.updateBook(book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        Optional<BookEntity> bookOptional = bookService.getBookById(id);
        if (bookOptional.isPresent()) {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<BookEntity>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping(params = "isbn")
    public ResponseEntity<BookEntity> getBook(@RequestParam String isbn) {
        Optional<BookEntity> bookOptional = bookService.getBook(isbn);
        if (bookOptional.isPresent()) {
            return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(params = "author")
    public ResponseEntity<List<BookEntity>> searchBook(@RequestParam String author) {
        Optional<List<BookEntity>> bookOptional = bookService.searchBook(author);

        if (bookOptional.isPresent()) {
            return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
