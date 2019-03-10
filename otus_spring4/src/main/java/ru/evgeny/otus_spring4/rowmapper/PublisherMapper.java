package ru.evgeny.otus_spring4.rowmapper;

import ru.evgeny.otus_spring4.domain.Publisher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.evgeny.otus_spring4.rowmapper.MapperConstant.*;

public class PublisherMapper implements RowMapper<Publisher> {


    public Publisher mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        String address = resultSet.getString(ADDRESS);
        String phone = resultSet.getString(PHONE);
        return new Publisher(id, name, address, phone);
    }
}
