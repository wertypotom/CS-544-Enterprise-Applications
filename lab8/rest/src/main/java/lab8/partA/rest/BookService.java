package lab8.partA.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Optional<BookEntity> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public BookEntity addBook(BookEntity book) {
        return bookRepository.save(book);
    }

    public BookEntity updateBook(BookEntity book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<BookEntity> getBook(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Optional<List<BookEntity>> searchBook(String author) {
        return bookRepository.findByAuthor(author);
    }

}
