package ru.evgeny.otus_spring4.dao.interfaces;

import ru.evgeny.otus_spring4.domain.Author;

import java.util.List;

public interface AuthorDao extends TableCreatable, TableDropable {
    List<Author> getByName(String firstname);

    List<Author> getByLastName(String lastname);

    Author getById(long id);

    long count();

    void insert(Author author);
}
