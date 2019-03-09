package dao.interfaces;

import domain.Publisher;

import java.util.List;

public interface PublisherDao {
    void insert(Publisher publisher);

    long count();

    List<Publisher> getByName(String name);

    Publisher getById(long id);
}
