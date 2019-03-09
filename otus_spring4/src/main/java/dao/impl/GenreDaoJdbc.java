package dao.impl;

import dao.helper.SqlHelper;
import dao.interfaces.GenreDao;
import domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import rowmapper.GenreMapper;
import rowmapper.MapperConstant;

import static dao.helper.SqlHelper.*;
import static rowmapper.MapperConstant.NAME;


@Repository
public class GenreDaoJdbc implements GenreDao {

    private static String TABLE_NAME = "genre";
    private JdbcOperations jdbcOperations;

    @Autowired
    public GenreDaoJdbc(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }



    public Genre getById(long id) {
        return query(SELECT + TABLE_NAME + WHERE + MapperConstant.ID + CONDITION, new Object[]{id});
    }

    public long cout() {
        return count(jdbcOperations, TABLE_NAME);
    }

    public Genre getByName(String name) {
        return query(SELECT + TABLE_NAME + WHERE + MapperConstant.NAME + CONDITION, new Object[]{name});
    }

    public void insert(Genre genre) {
        String sql = String.format("insert into %s (%s) values (?)", TABLE_NAME, NAME);
        jdbcOperations.update(sql, genre.getName());
    }

    private Genre query(String sql, Object[] parameters) {
        return jdbcOperations.queryForObject(sql, parameters, new GenreMapper());
    }

    public void createTable() {
        createGenreTable(jdbcOperations);
    }

    @Override
    public void dropTable() {
        SqlHelper.dropTable(jdbcOperations, TABLE_NAME);
    }
}
