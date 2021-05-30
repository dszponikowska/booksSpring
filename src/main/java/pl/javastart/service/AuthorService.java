package pl.javastart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.dto.AuthorDto;
import pl.javastart.model.Author;
import pl.javastart.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public AuthorDto getAuthorById(Long id) {
        Author foundAuthor = authorRepository.findById(id).get();
        AuthorDto foundAuthorDto = new AuthorDto(foundAuthor);
        return foundAuthorDto;
    }

    public List<AuthorDto> getAllAuthors() {
        Iterable<Author> allAuthors = authorRepository.findAll();
        List<Author> allAuthorsList = new ArrayList<>();
        allAuthors.forEach(allAuthorsList::add);
        List<AuthorDto> allAuthorsDtoList = allAuthorsList
                .stream()
                .map(author -> new AuthorDto(author))
                .collect(Collectors.toList());
        return allAuthorsDtoList;
    }

    public List<AuthorDto> getAuthorsByLastName(String lastName) {
        if (lastName == null) {
           return getAllAuthors();
        }
            List<Author> authorsByLastName = authorRepository.findByLastName(lastName);
        List<AuthorDto> authorsDtoByLastName = authorsByLastName.stream().
                map(AuthorDto::new).
                collect(Collectors.toList());
        return authorsDtoByLastName;
    }

    //działajaca metoda
//    public Author addAuthor(AuthorDto authorDto) {
//        Author author = new Author(authorDto.getName(), authorDto.getLastName());
//        Author savedAuthor = authorRepository.save(author);
//        return savedAuthor;
//    }


    public AuthorDto addAuthor(AuthorDto authorDto) {
        Author author = new Author(authorDto.getName(), authorDto.getLastName());
        Author savedAuthor = authorRepository.save(author);
        AuthorDto authorDto1 = new AuthorDto(savedAuthor);
        return authorDto1;

    }

    public AuthorDto updateLastNameAuthor(AuthorDto authorDto, Long id) {
        Author authorToUpdate = authorRepository.findById(id).get();
        authorToUpdate.setLastName(authorDto.getLastName());
        authorToUpdate.setName(authorDto.getName());
        Author updatedAuthor = authorRepository.save(authorToUpdate);
        AuthorDto updatedAuthorDto = new AuthorDto(updatedAuthor);
        return updatedAuthorDto;
    }


    public AuthorDto deleteAuthorById(Long id) {
        Author authorToDelete = authorRepository.findById(id).get();
        authorRepository.delete(authorToDelete);
        AuthorDto authorDtoDeleted = new AuthorDto(authorToDelete);
        return authorDtoDeleted;
    }


}




