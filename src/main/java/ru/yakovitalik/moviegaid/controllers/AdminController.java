package ru.yakovitalik.moviegaid.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.yakovitalik.moviegaid.dao.MovieDAO;
import ru.yakovitalik.moviegaid.models.Movie;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final MovieDAO movieDAO;

    @Autowired
    public AdminController(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("movies", movieDAO.index());
        return "admin/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("movie", movieDAO.show(id));
        return "admin/show";
    }

    @GetMapping("/new")
    public String newMovie(@ModelAttribute("movie") Movie movie) {
        return "admin/new";
    }

    @PostMapping
    public String create(@ModelAttribute("movie") @Valid Movie movie,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "admin/new";
        movieDAO.save(movie);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("movie", movieDAO.show(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("movie") @Valid Movie movie,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors())
            return "admin/edit";
        movieDAO.update(id, movie);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        movieDAO.delete(id);
        return "redirect:/admin";
    }
}


