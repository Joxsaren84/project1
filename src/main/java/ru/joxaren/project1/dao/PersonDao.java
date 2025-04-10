package ru.joxaren.project1.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.joxaren.project1.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {

    private final JdbcTemplate jdbcTemplate;

    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List <Person> index(){
        String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person){
        String sql = "INSERT INTO person (person_name, person_born_year) VALUES (?, ?)";
        jdbcTemplate.update(sql, person.getPersonName(), person.getPersonBornYear());
    }
}
