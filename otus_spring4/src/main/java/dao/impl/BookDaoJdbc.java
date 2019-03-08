package dao.impl;

import dao.interfaces.AuthorDao;
import dao.interfaces.BookDao;
import dao.interfaces.GenreDao;
import dao.interfaces.PublisherDao;
import domain.Author;
import domain.Book;
import domain.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import rowmapper.BookMapper;

import java.util.List;
import java.util.Map;

import static rowmapper.MapperConstant.*;

@Repository
public class BookDaoJdbc implements BookDao {

    private static String TABLE_NAME = "book";
    private static String SELECT = "select * from ";

    private JdbcOperations jdbcOperations;
    private AuthorDao authorDao;
    private GenreDao genreDao;
    private PublisherDao publisherDao;

    @Autowired
    public BookDaoJdbc(JdbcOperations jdbcOperations, AuthorDao authorDao, GenreDao genreDao, PublisherDao publisherDao) {
        this.jdbcOperations = jdbcOperations;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.publisherDao = publisherDao;
    }

    public long count() {
        return jdbcOperations.queryForObject("select count(*) from " + TABLE_NAME , Integer.class);
    }

    public Book getById(long id) {
        return jdbcOperations
                .queryForObject(SELECT + TABLE_NAME + " where " + ID +" = ?", new Object[]{id},
                        new BookMapper(authorDao, genreDao, publisherDao));
    }

    public List<Book> getByAuthor(Author author) {
        return query(SELECT + TABLE_NAME + " where " + AUTHOR +" = ?", new Object[]{author.getId()});
    }

    public List<Book> getByName(String name) {
        return query(SELECT + TABLE_NAME + " where name = ?", new Object[]{name});
    }

    public List<Book> getByPublisher(Publisher publisher) {
        return query(SELECT + TABLE_NAME + " where " + PUBLISHER + " = ?", new Object[]{publisher.getId()});
    }

    public List<Book> getAll() {
        return jdbcOperations.queryForList(SELECT + TABLE_NAME, Book.class, new BookMapper(authorDao, genreDao, publisherDao));
    }

    public void insert(Book book) {
        String sql = String.format("insert in to %s (%s, %s, %s, %s, $s) values (?, ?, ?, ?, ?", ID, NAME, AUTHOR, GENRE, PUBLISHER);
        jdbcOperations.update(sql, book.getId(), book.getName(), book.getAuthor(), book.getGenre(), book.getPublisher());
    }

    private List<Book> query(String sql, Object[] parameters) {
        List<Book> books = jdbcOperations.query(sql, parameters, new BookMapper(authorDao, genreDao, publisherDao));
        return books;
    }
}
