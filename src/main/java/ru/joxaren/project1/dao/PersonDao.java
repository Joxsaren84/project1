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

    public Person getPerson(int id){
        String sql = "SELECT * FROM person WHERE person_id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void personUpdate(int id, Person person){
        String sql = "UPDATE person SET person_name = ?, person_born_year = ? WHERE person_id = ?";
        jdbcTemplate.update(sql, person.getPersonName(), person.getPersonBornYear(), id);
    }

    public void deletePerson(int id){
        String sql="DELETE FROM person WHERE person_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
