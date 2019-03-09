package dao.interfaces;

import domain.Genre;

public interface GenreDao extends TableCreatable, TableDropable {
    Genre getById(long id);

    long cout();

    Genre getByName(String name);

    void insert(Genre genre);


}
