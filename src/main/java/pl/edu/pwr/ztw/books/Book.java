package pl.edu.pwr.ztw.books;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public @Getter
@Setter

class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int    id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Author author;

    int pages;
}