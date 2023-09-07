package ru.leskov.musicShop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.leskov.musicShop.DAO.GameDAO;
import ru.leskov.musicShop.DAO.PersonDAO;
import ru.leskov.musicShop.models.Game;

import javax.validation.Valid;

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
    public String index(Model model) {
        model.addAttribute("games", gameDAO.index());
        return "games/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("game", gameDAO.show(id));
        return "games/show";
    }

    @GetMapping("/new")
    public String newGame(Model model) {
        model.addAttribute("newGame", new Game());
        return "games/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("newGame") @Valid Game game,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "games/new";
        gameDAO.save(game);

        return "redirect:/games";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("updateGame", gameDAO.show(id));
        return "games/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("updateGame") @Valid Game game,
                         BindingResult bindingResult,
                         @PathVariable("id") int id
                         ){
        if (bindingResult.hasErrors())
            return "games/edit";
        gameDAO.update(id,game);
        return "redirect:/games";
    }
}
