package ru.evgeny.otus_spring4.dao.impl;

import ru.evgeny.otus_spring4.dao.helper.SqlHelper;
import ru.evgeny.otus_spring4.dao.interfaces.PublisherDao;
import ru.evgeny.otus_spring4.domain.Publisher;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.evgeny.otus_spring4.rowmapper.PublisherMapper;

import java.util.List;

import static ru.evgeny.otus_spring4.dao.helper.SqlHelper.CONDITION;
import static ru.evgeny.otus_spring4.dao.helper.SqlHelper.SELECT;
import static ru.evgeny.otus_spring4.dao.helper.SqlHelper.WHERE;
import static ru.evgeny.otus_spring4.rowmapper.MapperConstant.*;

@Repository
public class PublisherDaoJdbc implements PublisherDao {

    private JdbcOperations jdbcOperations;
    private static String TABLE_NAME = "publisher";

    public PublisherDaoJdbc(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void insert(Publisher publisher) {
        String sql = String.format("insert into %s (%s, %s, %s) values (?, ?, ?)", TABLE_NAME, NAME, ADDRESS, PHONE);
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


    @Override
    public void createTable() {
        SqlHelper.createPublisherTable(jdbcOperations);
    }

    @Override
    public void dropTable() {
        SqlHelper.dropTable(jdbcOperations, TABLE_NAME);
    }
}
