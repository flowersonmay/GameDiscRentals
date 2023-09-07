package ru.leskov.musicShop.DAO;

import jdk.nashorn.internal.scripts.JD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.leskov.musicShop.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Person show(int id) {
        Optional<Person> optionalPerson = jdbcTemplate.query(
                "SELECT * FROM Person WHERE id_person=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
        return optionalPerson.orElse(null);
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person (fullName,yearsOfBirthday) VALUES(?,?)",
                person.getFullName(),person.getYearsOfBirthday());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET fullName=?, yearsOfBirthday=? WHERE id_person=?",
                person.getFullName(),person.getYearsOfBirthday(),id);

    }
}
