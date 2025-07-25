package domain;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String author;

    private String isbn;

    @ManyToOne()
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    public Book() {}

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
