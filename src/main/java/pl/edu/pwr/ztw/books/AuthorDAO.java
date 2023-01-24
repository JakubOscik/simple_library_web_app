package pl.edu.pwr.ztw.books;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface AuthorDAO extends CrudRepository<Author, Integer> {
    @Transactional
    @Modifying
    @Query("update Author a set a.name = ?1, a.surname = ?2 where a.id = ?3")
    int updateNameAndSurnameById(String name, String surname, int id);

}