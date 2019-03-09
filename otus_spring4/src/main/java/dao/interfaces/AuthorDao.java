package dao.interfaces;

import domain.Author;

import java.util.List;

public interface AuthorDao extends TableCreatable {
    List<Author> getByName(String firstname);

    List<Author> getByLastName(String lastname);

    Author getById(long id);

    long count();

    void insert(Author author);
}
