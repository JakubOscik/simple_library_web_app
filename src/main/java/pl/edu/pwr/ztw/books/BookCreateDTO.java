package pl.edu.pwr.ztw.books;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data
class BookCreateDTO {
    private String title;
    private Integer pages;
    private int authorId;
}
