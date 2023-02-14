package ru.yakovitalik.ourcinema.controllers;

// контроллер для объединения баз актеров и фильмов(общие запросы)

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.yakovitalik.ourcinema.dao.MovieActorDAO;
import ru.yakovitalik.ourcinema.models.Actor;
import ru.yakovitalik.ourcinema.models.Movie;
import ru.yakovitalik.ourcinema.util.ActorValidator;

import javax.validation.Valid;


@Controller
@RequestMapping("/union")
public class UnionController {
    private final MovieActorDAO movieActorDAO;
    private final ActorValidator actorValidator;

    @Autowired
    public UnionController(MovieActorDAO movieActorDAO, ActorValidator actorValidator) {
        this.movieActorDAO = movieActorDAO;
        this.actorValidator = actorValidator;
    }

    // вывести список актеров для конкретного фильма
    @GetMapping()
    public String showList(@RequestParam("movid") int movieId, Model model) {
        model.addAttribute("actors", movieActorDAO.showList(movieId));
        model.addAttribute("movie", movieActorDAO.getMovie(movieId));
        return "union/actors";
    }

    // создание нового актера(отдельное, для привязки к фильму)
    // метод для перехода на страницу создания нового актера и заполнения данных
    @GetMapping("/new")
    public String newActor(@RequestParam("movid") int movieId, @ModelAttribute("actor") Actor actor,
                           Model model) {
        model.addAttribute("movie", movieActorDAO.getMovie(movieId));
        return "union/new";
    }

    // метод для создания нового актера и добавления к фильму
    @PostMapping
    public String create(@RequestParam("movid") int movieId, @ModelAttribute("actor") @Valid Actor actor,
                         BindingResult bindingResult, Model model) {
        actorValidator.validate(actor, bindingResult);

        if(bindingResult.hasErrors())
            return "actors/new";
        int savedId = movieActorDAO.saveActor(actor);
        movieActorDAO.add(movieId, savedId);
        model.addAttribute("movie", movieActorDAO.getMovie(movieId));
        model.addAttribute("actor", movieActorDAO.getActor(savedId));
        return "redirect:/union" + "?movid=" + movieId + "&actid=" + savedId;
    }

    @GetMapping("/search/{id}")
    public String searchActor(@PathVariable("id") int movieId, Model model) {
        model.addAttribute("movie", movieActorDAO.getMovie(movieId));
        return "union/search";
    }

    // вывод страницы с данными конкретного актера
    @GetMapping("/show/{id}")
    public String showActor(@PathVariable("id") int movieId, @RequestParam("name") String actorName,
                            Model model) {
        Actor findActor = movieActorDAO.showActor(actorName);
        if(findActor == null) {
            return "union/nosearch";
        } else {
            model.addAttribute("movie", movieActorDAO.getMovie(movieId));
            model.addAttribute("actor", findActor);
            return "union/show";
        }
    }

    // привязать только что созданного актера к фильму
    @PostMapping("/connect")
    public String connect(@RequestParam("id") int movieId, @RequestParam("name") String actorName) {

        Actor actor = movieActorDAO.showActor(actorName);
        int actId = actor.getId();
        movieActorDAO.add(movieId, actId);
        return "redirect:/union" + "?movid=" + movieId + "&actid=" + actId;
    }


    // переход на страницу добавления
//    @GetMapping("/add")
//    public String addActor(@RequestParam("id") int movieId,
//                           @ModelAttribute("actor") Actor actor) {
//        movieActorDAO.add(movieId, actor.getName());
//        return "union/add";
//    }

}
