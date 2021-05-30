package pl.javastart.dto;

public class CreateBookDto {

    private String name;
    private Long authorId;

    public CreateBookDto(String name, Long authorId) {
        this.name = name;
        this.authorId = authorId;
    }

    public CreateBookDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
