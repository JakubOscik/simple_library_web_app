package pl.edu.pwr.ztw.books;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public @Getter @Setter
class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int       id;
    private String    name;
    private String    surname;

    @OneToMany(mappedBy = "author", cascade=CascadeType.ALL, fetch = FetchType.LAZY)

    @JsonIgnore
    private Set<Book> books;
}
