package pl.javastart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;
import pl.javastart.dto.BookDto;
import pl.javastart.dto.CreateBookDto;
import pl.javastart.exception.BadRequestException;
import pl.javastart.exception.NotFoundException;
import pl.javastart.model.Author;
import pl.javastart.model.Book;
import pl.javastart.repository.AuthorRepository;
import pl.javastart.repository.BookRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    public AuthorRepository authorRepository;

    public Iterable<BookDto> getBooks(String name, Long authorId) {
        if (name != null) {
            List<Book> booksByName = bookRepository.findByName(name);
           return mapBookListToBookDtoList(booksByName);
        } else if (authorId!=null){
            List<Book> byAuthorId = bookRepository.findByAuthorId(authorId);
            return mapBookListToBookDtoList(byAuthorId);
        } else {
            Iterable<Book> allBooks = bookRepository.findAll();
            List<Book> booksList = new ArrayList<>();
            allBooks.forEach(book -> booksList.add(book));
            return mapBookListToBookDtoList(booksList);
        }
    }

    private Iterable<BookDto> mapBookListToBookDtoList(List<Book> books){
        List<BookDto> booksDtoList = books
                .stream()
                .map(book -> new BookDto(book))
                .collect(Collectors.toList());
        return booksDtoList;
    }

    public BookDto getBookById(Long id) {
        Book foundBook = bookRepository.findById(id).get();
        BookDto bookDto = new BookDto(foundBook);
        return bookDto;
    }

    public BookDto addBook(CreateBookDto createBookDto) throws NotFoundException, BadRequestException {
        if (createBookDto.getName().contains("fuck")) {
            throw new BadRequestException("Can't contain fuck");
        }
        if (createBookDto.getName().contains("shit")) {
            throw new BadRequestException("Can't contain shit");
        }
        Optional<Author> optionalAuthor = authorRepository.findById(createBookDto.getAuthorId());
        if (optionalAuthor.isPresent()) {
            Book book = new Book(createBookDto.getName(), optionalAuthor.get());
            Book savedBook = bookRepository.save(book);
            BookDto savedBookDto = new BookDto(savedBook);
            return savedBookDto;
        } else {
            throw new NotFoundException();
        }
    }

    public BookDto updateBook(BookDto bookDto, Long id) {
        Optional<Book> byId = bookRepository.findById(id);
        Book foundBook = byId.get();
        foundBook.setName(bookDto.getName());
        Book updatedBook = bookRepository.save(foundBook);
        BookDto updatedBookDto = new BookDto(updatedBook);
        return updatedBookDto;
    }

    public BookDto deleteBookById(Long id) {
        Book bookToDelete = bookRepository.findById(id).get();
        bookRepository.delete(bookToDelete);
        BookDto bookDtoToDelete = new BookDto(bookToDelete);
        return bookDtoToDelete;
    }


}
