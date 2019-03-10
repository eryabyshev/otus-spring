package ru.evgeny.otus_spring4.rowmapper;

import ru.evgeny.otus_spring4.domain.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.evgeny.otus_spring4.rowmapper.MapperConstant.FIRST_NAME;
import static ru.evgeny.otus_spring4.rowmapper.MapperConstant.ID;
import static ru.evgeny.otus_spring4.rowmapper.MapperConstant.LAST_NAME;

public class AuthorMapper implements RowMapper<Author> {

    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong(ID);
        String firstname = resultSet.getString(FIRST_NAME);
        String lastname = resultSet.getString(LAST_NAME);
        return new Author(id, firstname, lastname);
    }
}
