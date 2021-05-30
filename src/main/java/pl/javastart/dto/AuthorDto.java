package pl.javastart.dto;

import pl.javastart.model.Author;

public class AuthorDto {

    private Long id;
    private String name;
    private String lastName;

    public AuthorDto(Long id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    public AuthorDto(Author author){
        this.id = author.getId();
        this.name = author.getName();
        this.lastName = author.getLastName();
    }


    public AuthorDto() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
