package ru.evgeny.otus_spring4.dao.interfaces;

import ru.evgeny.otus_spring4.domain.Publisher;

import java.util.List;

public interface PublisherDao extends TableCreatable, TableDropable {
    void insert(Publisher publisher);

    long count();

    List<Publisher> getByName(String name);

    Publisher getById(long id);
}
