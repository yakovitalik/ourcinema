package ru.yakovitalik.ourcinema.controllers;

// контроллер для объединения баз актеров и фильмов(общие запросы)

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yakovitalik.ourcinema.dao.MovieActorDAO;

import java.util.List;


@Controller
@RequestMapping("/union")
public class UnionController {
    private final MovieActorDAO movieActorDAO;

    @Autowired
    public UnionController(MovieActorDAO movieActorDAO) {
        this.movieActorDAO = movieActorDAO;
    }

    // вывести список актеров для конкретного фильма
    @GetMapping("/{id}")
    public String showList(@PathVariable("id") int movieId, Model model) {
        model.addAttribute("actors", movieActorDAO.showList(movieId));
        model.addAttribute("movie", movieActorDAO.getMovie(movieId));
        return "union/actors";
    }
}
