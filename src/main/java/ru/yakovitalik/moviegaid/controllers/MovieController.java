package ru.yakovitalik.moviegaid.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.yakovitalik.moviegaid.dao.ActorDAO;
import ru.yakovitalik.moviegaid.dao.MovieDAO;


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

