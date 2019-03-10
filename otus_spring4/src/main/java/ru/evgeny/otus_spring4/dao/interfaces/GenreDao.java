package ru.evgeny.otus_spring4.dao.interfaces;

import ru.evgeny.otus_spring4.domain.Genre;

public interface GenreDao extends TableCreatable, TableDropable {
    Genre getById(long id);

    long cout();

    Genre getByName(String name);

    void insert(Genre genre);


}
