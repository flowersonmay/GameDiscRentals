package ru.leskov.musicShop.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.leskov.musicShop.models.Game;
import ru.leskov.musicShop.models.Person;

import java.util.List;
import java.util.Optional;

@Component

public class GameDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Game> index() {
        return jdbcTemplate.query("SELECT * FROM Game", new BeanPropertyRowMapper<>(Game.class));
    }

    public Game show(int id) {
        Optional<Game> optionalGame = jdbcTemplate.query("SELECT * FROM Game WHERE id_game=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Game.class)).stream().findAny();
        return optionalGame.orElse(null);
    }

    public void save(Game game) {
        jdbcTemplate.update("INSERT INTO game (title, publisher, year) VALUES(?,?,?)",
                game.getTitle(), game.getPublisher(), game.getYear());
        System.out.println("ADD HAS BEEN DONE");
        //insert into game(title, publisher, year, id_person) VALUES
    }

    public void update(int id, Game game) {
        jdbcTemplate.update("UPDATE game SET title=?, publisher=?, year=? WHERE id_game=?",
                game.getTitle(), game.getPublisher(), game.getYear(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM game WHERE id_game=?",id);
    }

    public Optional<Person> getGameOwner(int id) {
        return jdbcTemplate.query(
                "select person.* from game join  person on person.id_person = game.person_id\n" +
                "where id_game=?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void release(int id){
        jdbcTemplate.update("UPDATE game SET person_id=NULL WHERE id_game=?", id);
    }

    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE game set person_id=? WHERE id_game=?",selectedPerson.getId_person(),id);
    }
}
