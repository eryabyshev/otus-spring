package dao.impl;


import dao.interfaces.AuthorDao;
import domain.Author;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDaoJdbc implements AuthorDao {
    public List<Author> getByName(String firstname) {
        return null;
    }

    public List<Author> getByLastName(String lastname) {
        return null;
    }

    public Author getById(long id) {
        return null;
    }

    public long count() {
        return 0;
    }

    public boolean insert(Author author) {
        return false;
    }
}
