package ru.leskov.musicShop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.leskov.musicShop.DAO.GameDAO;
import ru.leskov.musicShop.DAO.PersonDAO;

@Controller
@RequestMapping("/games")
public class GameController {

    private final PersonDAO personDAO;
    private final GameDAO gameDAO;

    @Autowired
    public GameController(PersonDAO personDAO, GameDAO gameDAO) {
        this.personDAO = personDAO;
        this.gameDAO = gameDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("games",gameDAO.index());
        return "games/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("game",gameDAO.show(id));
        return "games/show";
    }
}
