package pl.javastart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import pl.javastart.dto.AuthorDto;
import pl.javastart.model.Author;
import pl.javastart.repository.AuthorRepository;
import pl.javastart.service.AuthorService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
public class AuthorController {


    @Autowired
    public AuthorService authorService;



    @GetMapping(value = "/authors/{id}")
    public AuthorDto getAuthorById(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping(value = "/authors")
    public List<AuthorDto> getAuthorsByLastName(@RequestParam(value = "lastName", required = false) String lastName) {
        return authorService.getAuthorsByLastName(lastName);
    }

    //tu też zmienilam requestBOdy na autor zamiast authorDto
    @PostMapping(value = "/authors")
    public AuthorDto addAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.addAuthor(authorDto);
    }

    @PutMapping(value = "/authors/{id}")
    public AuthorDto updateAuthorWithLastName(
            @RequestBody AuthorDto authorDto,
            @PathVariable("id") Long id
    ) {
        return authorService.updateLastNameAuthor(authorDto, id);
    }

    @DeleteMapping(value = "/authors/{id}")
    public AuthorDto deleteAuthorById(@PathVariable("id") Long id) {
        return authorService.deleteAuthorById(id);
    }


}

