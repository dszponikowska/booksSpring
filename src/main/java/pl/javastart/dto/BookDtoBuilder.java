package pl.javastart.dto;

public class BookDtoBuilder {

    private BookDto bookDto;

    public BookDtoBuilder() {
        this.bookDto = new BookDto();
    }

    public void setName(String name) {
        bookDto.setName(name);
    }

    public BookDto build() {
        return bookDto;
    }

}
