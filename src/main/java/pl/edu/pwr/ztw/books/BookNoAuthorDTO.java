package pl.edu.pwr.ztw.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data
class BookNoAuthorDTO {
    private int id;
    private String title;
    private Integer pages;
}