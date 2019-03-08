package dao.interfaces;

import domain.Genre;

import java.util.List;

public interface GenreDao {
    Genre getById(long id);
    long cout();
    Genre getByName(String name);
    boolean insert(Genre genre);


}
