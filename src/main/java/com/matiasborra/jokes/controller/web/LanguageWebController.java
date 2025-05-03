package com.matiasborra.jokes.controller.web;

import com.matiasborra.jokes.dto.LanguageDTO;
import com.matiasborra.jokes.model.entity.Language;
import com.matiasborra.jokes.model.services.ILanguageService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/languages")
public class LanguageWebController {

    private final ILanguageService languageService;

    public LanguageWebController(ILanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("languages", languageService.findAll());
        return "languages/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("language", new Language());
        return "languages/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("language") LanguageDTO language,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "languages/form";
        }
        languageService.create(language);
        return "redirect:/languages";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("language", languageService.findById(id));
        return "languages/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("language") LanguageDTO language,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "languages/form";
        }
        languageService.update(id, language);
        return "redirect:/languages";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        languageService.delete(id);
        return "redirect:/languages";
    }
}
