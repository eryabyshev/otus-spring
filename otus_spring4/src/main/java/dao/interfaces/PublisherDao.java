package dao.interfaces;

import domain.Publisher;

import java.util.List;

public interface PublisherDao {
    boolean insert(Publisher publisher);
    long count();
    List<Publisher> getBuName(String name);
    Publisher getById(long id);
}
