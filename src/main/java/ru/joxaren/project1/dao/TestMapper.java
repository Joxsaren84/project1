package ru.joxaren.project1.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestMapper implements RowMapper<String> {
    @Override
    public String mapRow(ResultSet resultSet, int i) throws SQLException {

        String name = resultSet.getString("person_name");

        return name;
    }
}
