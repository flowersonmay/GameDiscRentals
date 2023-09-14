package ru.leskov.musicShop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.leskov.musicShop.DAO.GameDAO;
import ru.leskov.musicShop.DAO.PersonDAO;
import ru.leskov.musicShop.models.Game;
import ru.leskov.musicShop.models.Person;

import javax.validation.Valid;
import java.util.Optional;

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
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute("person")Person person) {
        model.addAttribute("game", gameDAO.show(id));

        Optional<Person> gameOwner = gameDAO.getGameOwner(id);
        if (gameOwner.isPresent()){
            model.addAttribute("owner",gameOwner.get());
        }else {
            model.addAttribute("people", personDAO.index());
        }
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
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        gameDAO.delete(id);
        return "redirect:/games";
    }
    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        gameDAO.release(id);
        return "redirect:/games/"+id;
    }
    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson){
        gameDAO.assign(id,selectedPerson);
        return "redirect:/games/"+id;
    }
}
