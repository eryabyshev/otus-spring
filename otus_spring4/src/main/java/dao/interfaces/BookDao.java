package dao.interfaces;

import domain.Author;
import domain.Book;
import domain.Publisher;

import java.util.List;

public interface BookDao extends TableCreatable, TableDropable {
    long count();

    Book getById(long id);

    List<Book> getByAuthor(Author author);

    List<Book> getByName(String name);

    List<Book> getByPublisher(Publisher publisher);

    List<Book> getAll();

    void insert(Book book);


}
