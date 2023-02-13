package ru.yakovitalik.ourcinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.yakovitalik.ourcinema.dao.MovieDAO;
import ru.yakovitalik.ourcinema.models.Movie;

import javax.validation.Valid;

// контролер для запросов относительно фильмов в режиме администратора
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final MovieDAO movieDAO;

    @Autowired
    public AdminController(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    // метод для вывода страницы с фильмами(режим админа)
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("movies", movieDAO.index());
        return "admin/index";
    }

    // метод для вывода данных конкретного фильма(режим админа)
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("movie", movieDAO.show(id));
        return "admin/show";
    }

    // метод для перехода на страницу создания нового фильма и заполнения данных
    @GetMapping("/new")
    public String newMovie(@ModelAttribute("movie") Movie movie) {
        return "admin/new";
    }

    // метод для создания нового фильма
    @PostMapping
    public String create(@ModelAttribute("movie") @Valid Movie movie,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "admin/new";
        movieDAO.save(movie);
        return "redirect:/admin";
    }

    // метод для редактирования существующего фильма
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("movie", movieDAO.show(id));
        return "admin/edit";
    }

    // метод для обновления данных существующего фильма
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("movie") @Valid Movie movie,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors())
            return "admin/edit";
        movieDAO.update(id, movie);
        return "redirect:/admin";
    }

    // метод для удаления фильма
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        movieDAO.delete(id);
        return "redirect:/admin";
    }
}


