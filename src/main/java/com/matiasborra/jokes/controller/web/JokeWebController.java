package com.matiasborra.jokes.controller;

import com.matiasborra.jokes.dto.JokeDTO;
import com.matiasborra.jokes.model.services.ICategoryService;
import com.matiasborra.jokes.model.services.IJokeService;
import com.matiasborra.jokes.model.services.ITypeService;
import com.matiasborra.jokes.model.services.ILanguageService;
import com.matiasborra.jokes.model.services.IFlagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jokes")
public class JokeWebController {

    private final IJokeService jokeService;
    private final ICategoryService categoryService;
    private final ITypeService typeService;
    private final ILanguageService languageService;
    private final IFlagService flagService;

    public JokeWebController(IJokeService jokeService,
                             ICategoryService categoryService,
                             ITypeService typeService,
                             ILanguageService languageService,
                             IFlagService flagService) {
        this.jokeService = jokeService;
        this.categoryService = categoryService;
        this.typeService = typeService;
        this.languageService = languageService;
        this.flagService = flagService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("jokes", jokeService.findAll());
        return "jokes/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("joke", new JokeDTO());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("languages", languageService.findAll());
        model.addAttribute("allFlags", flagService.findAll());
        return "jokes/form";
    }

    @GetMapping("/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("joke", jokeService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("languages", languageService.findAll());
        model.addAttribute("allFlags", flagService.findAll());
        return "jokes/form";
    }

    @PostMapping
    public String create(@ModelAttribute("joke") JokeDTO joke) {
        jokeService.create(joke);
        return "redirect:/jokes";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("joke") JokeDTO joke) {
        jokeService.update(id, joke);
        return "redirect:/jokes";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        jokeService.delete(id);
        return "redirect:/jokes";
    }
}
