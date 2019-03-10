package ru.evgeny.otus_spring4.rowmapper;

import ru.evgeny.otus_spring4.dao.interfaces.AuthorDao;
import ru.evgeny.otus_spring4.dao.interfaces.GenreDao;
import ru.evgeny.otus_spring4.dao.interfaces.PublisherDao;
import ru.evgeny.otus_spring4.domain.Author;
import ru.evgeny.otus_spring4.domain.Book;
import ru.evgeny.otus_spring4.domain.Genre;
import ru.evgeny.otus_spring4.domain.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.evgeny.otus_spring4.rowmapper.MapperConstant.*;

@Component
public class BookMapper implements RowMapper<Book> {

    private AuthorDao authorDao;
    private GenreDao genreDao;
    private PublisherDao publisherDao;


    @Autowired
    public BookMapper(AuthorDao authorDao, GenreDao genreDao, PublisherDao publisherDao) {
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.publisherDao = publisherDao;
    }

    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        Author boolAuthor = authorDao.getById(resultSet.getLong(AUTHOR));
        Genre genre = genreDao.getById(resultSet.getLong(GENRE));
        Publisher publisher = publisherDao.getById(resultSet.getLong(PUBLISHER));
        return new Book(id, name, boolAuthor, genre, publisher);
    }
}
