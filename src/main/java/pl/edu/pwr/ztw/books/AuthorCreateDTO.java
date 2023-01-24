package pl.edu.pwr.ztw.books;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class AuthorCreateDTO {
    private String    name;
    private String    surname;
}