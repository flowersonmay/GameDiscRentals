package ru.leskov.musicShop.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.leskov.musicShop.models.Game;
import ru.leskov.musicShop.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        Optional<Person> optionalPerson = jdbcTemplate.query(
                "SELECT * FROM Person WHERE id_person=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
        return optionalPerson.orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person (fullName,yearsOfBirthday) VALUES(?,?)",
                person.getFullName(),person.getYearsOfBirthday());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET fullName=?, yearsOfBirthday=? WHERE id_person=?",
                person.getFullName(),person.getYearsOfBirthday(),id);

    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person where id_person=?",id);
    }

    public List<Game> getGamesByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM game WHERE person_id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Game.class));

    }


}
