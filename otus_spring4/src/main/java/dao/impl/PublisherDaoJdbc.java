package dao.impl;

import dao.helper.SqlHelper;
import dao.interfaces.PublisherDao;
import domain.Publisher;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import rowmapper.PublisherMapper;

import java.util.List;

import static dao.helper.SqlHelper.CONDITION;
import static dao.helper.SqlHelper.SELECT;
import static dao.helper.SqlHelper.WHERE;
import static rowmapper.MapperConstant.*;

@Repository
public class PublisherDaoJdbc implements PublisherDao {

    private JdbcOperations jdbcOperations;
    private static String TABLE_NAME = "publisher";

    public PublisherDaoJdbc(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void insert(Publisher publisher) {
        String sql = String.format("insert in to %s (%s, %s, %s) values (?, ?, ?)", TABLE_NAME, NAME, ADDRESS, PHONE);
        jdbcOperations.update(sql, publisher.getName(), publisher.getAddress(), publisher.getPhone());
    }

    public long count() {
        return SqlHelper.count(jdbcOperations, TABLE_NAME);
    }

    public List<Publisher> getByName(String name) {
        return query(SELECT + TABLE_NAME + WHERE + NAME + CONDITION, new Object[]{name});
    }

    public Publisher getById(long id) {
        return jdbcOperations.queryForObject(SELECT + TABLE_NAME + WHERE + ID + CONDITION, new Object[]{id}, new PublisherMapper());
    }

    private List<Publisher> query(String sql, Object[] parameters) {
        return jdbcOperations.query(sql, parameters, new PublisherMapper());
    }


}
