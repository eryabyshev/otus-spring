package rowmapper;

import dao.interfaces.AuthorDao;
import dao.interfaces.GenreDao;
import dao.interfaces.PublisherDao;
import domain.Author;
import domain.Book;
import domain.Genre;
import domain.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static rowmapper.MapperConstant.*;

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
