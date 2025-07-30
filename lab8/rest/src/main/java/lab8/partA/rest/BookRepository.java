package lab8.partA.rest;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    public Optional<List<BookEntity>> findByAuthor(String author);
    public Optional<BookEntity> findByIsbn(String isbn);
}
