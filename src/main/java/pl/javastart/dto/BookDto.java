package pl.javastart.dto;

import pl.javastart.model.Author;
import pl.javastart.model.Book;

public class BookDto {

    private Long id;
    private String name;
    private AuthorDto author;

    public BookDto(Long id, String name, AuthorDto author) {
        this.name = name;
        this.id = id;
        this.author = author;
    }

    public BookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        if (book.getAuthor() != null) {
            this.author = new AuthorDto(book.getAuthor());
        }
    }

    public BookDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }
}
