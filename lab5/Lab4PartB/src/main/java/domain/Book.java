package domain;

import jakarta.persistence.Entity;

@Entity
public class Book extends Product{
    private String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
