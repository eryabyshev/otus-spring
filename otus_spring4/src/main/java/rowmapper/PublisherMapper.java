package rowmapper;

import domain.Publisher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static rowmapper.MapperConstant.*;

public class PublisherMapper implements RowMapper<Publisher> {


    public Publisher mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        String address = resultSet.getString(ADDRESS);
        String phone = resultSet.getString(PHONE);
        return new Publisher(id, name, address, phone);
    }
}
