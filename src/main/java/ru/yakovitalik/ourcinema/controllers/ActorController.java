package ru.yakovitalik.ourcinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.yakovitalik.ourcinema.dao.ActorDAO;
import ru.yakovitalik.ourcinema.models.Actor;
import ru.yakovitalik.ourcinema.util.ActorValidator;

import javax.validation.Valid;

// контроллер для работы с актерами
@Controller
@RequestMapping("/actors")
public class ActorController {
    private final ActorDAO actorDAO;
    private final ActorValidator actorValidator;

    @Autowired
    public ActorController(ActorDAO actorDAO, ActorValidator actorValidator) {
        this.actorDAO = actorDAO;
        this.actorValidator = actorValidator;
    }

    // вывод списка всех актеров в базе данных
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("actors", actorDAO.index());
        return "actors/index";
    }

    // вывод страницы с данными конкретного актера
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("actor", actorDAO.show(id));
        return "actors/show";
    }

    // метод для перехода на страницу создания нового актера и заполнения данных
    @GetMapping("/new")
    public String newActor(@ModelAttribute("actor") Actor actor) {
        return "actors/new";
    }

    // метод для создания нового актера
    @PostMapping
    public String create(@ModelAttribute("actor") @Valid Actor actor,
                         BindingResult bindingResult) {
        actorValidator.validate(actor, bindingResult);

        if(bindingResult.hasErrors())
            return "actors/new";
        int savedId = actorDAO.save(actor);
        return "redirect:/actors" + "?actid=" + savedId;
    }

    // метод для редактирования существующего актера
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("actor", actorDAO.show(id));
        return "actors/edit";
    }

    // метод для обновления данных существующего актера
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("actor") @Valid Actor actor,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        actorValidator.validate(actor, bindingResult);

        if(bindingResult.hasErrors())
            return "actors/edit";
        actorDAO.update(id, actor);
        return "redirect:/actors";
    }

    // метод для удаления актера
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        actorDAO.delete(id);
        return "redirect:/actors";
    }

}
