package ru.yakovitalik.ourcinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.yakovitalik.ourcinema.dao.MovieDAO;

// контролер для запросов в режиме пользователя
@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieDAO movieDAO;

    @Autowired
    public MovieController(MovieDAO movieDAO) {

        this.movieDAO = movieDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("movies", movieDAO.index());
        return "movies/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("movie", movieDAO.show(id));
        return "movies/show";
    }
}

