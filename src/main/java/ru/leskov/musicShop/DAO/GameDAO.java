package ru.leskov.musicShop.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.leskov.musicShop.models.Game;

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
}
