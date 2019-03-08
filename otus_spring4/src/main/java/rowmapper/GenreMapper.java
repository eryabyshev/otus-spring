package rowmapper;

import domain.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static rowmapper.MapperConstant.ID;
import static rowmapper.MapperConstant.NAME;

public class GenreMapper implements RowMapper<Genre> {

    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong(ID);
        String genre = resultSet.getString(NAME);
        return new Genre(id, genre);
    }
}
