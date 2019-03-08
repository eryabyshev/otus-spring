package rowmapper;

import domain.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static rowmapper.MapperConstant.FIRST_NAME;
import static rowmapper.MapperConstant.ID;
import static rowmapper.MapperConstant.LAST_NAME;

public class AuthorMapper implements RowMapper<Author> {

    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong(ID);
        String firstname = resultSet.getString(FIRST_NAME);
        String lastname = resultSet.getString(LAST_NAME);
        return new Author(id, firstname, lastname);
    }
}
