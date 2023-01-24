package pl.edu.pwr.ztw.books;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class AuthorDTO {
    private int       id;
    private String    name;
    private String    surname;
}