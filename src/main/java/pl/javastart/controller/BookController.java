package pl.javastart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.javastart.dto.BookDto;
import pl.javastart.dto.BookDtoBuilder;
import pl.javastart.dto.CreateBookDto;
import pl.javastart.exception.BadRequestException;
import pl.javastart.exception.NotFoundException;
import pl.javastart.model.Book;
import pl.javastart.repository.BookRepository;
import pl.javastart.service.BookService;

import java.util.List;
import java.util.Optional;


@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/books")
    public Iterable<BookDto> getBooks(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "authorId", required = false) Long authorId
        ) {
        return bookService.getBooks(name, authorId);

    }

    @GetMapping(value = "/books/{id}")
    public BookDto getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping(value = "/books")
    public ResponseEntity addBook(@RequestBody CreateBookDto createBookDto) {
        try {
            BookDto bookDto = bookService.addBook(createBookDto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(bookDto);
        } catch (NotFoundException exception) {
            return ResponseEntity.notFound().build();
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping(value = "/books/{id}")
    public BookDto updateBook(
            @RequestBody BookDto bookDto,
            @PathVariable("id") Long id
    ) {
        return bookService.updateBook(bookDto, id);
    }

    @DeleteMapping(value = "/books/{id}")
    public BookDto deleteBookById(@PathVariable("id") Long id) {
        /**
         * It delets book and returns book dto.
         */
        return bookService.deleteBookById(id);
    }

}
