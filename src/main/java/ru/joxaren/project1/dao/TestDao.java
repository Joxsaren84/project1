package ru.joxaren.project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

@Component
public class TestDao {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String testDataBase(){
        String sql = "SELECT person_name FROM person WHERE person_id = ?";
        return jdbcTemplate.query(sql, new Object[]{1}, new TestMapper()).stream().findAny().orElse(null);
    }
}
