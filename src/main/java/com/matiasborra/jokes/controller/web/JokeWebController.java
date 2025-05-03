package com.matiasborra.jokes.controller.web;

import com.matiasborra.jokes.dto.JokeDTO;
import com.matiasborra.jokes.model.services.ICategoryService;
import com.matiasborra.jokes.model.services.IFlagService;
import com.matiasborra.jokes.model.services.ILanguageService;
import com.matiasborra.jokes.model.services.IJokeService;
import com.matiasborra.jokes.model.services.ITypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

//    @GetMapping
//    public String list(Model model) {
//        model.addAttribute("jokes", jokeService.findAll());
//        return "jokes/list";
//    }

    @GetMapping
    public String list(@RequestParam(required = false) String q, Model model) {
        List<JokeDTO> jokes;
        if (q != null && !q.isBlank()) {
            jokes = jokeService.filterByText(q);
        } else {
            jokes = jokeService.findAll();
        }
        model.addAttribute("jokes", jokes);
        model.addAttribute("q", q);
        return "jokes/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        JokeDTO dto = new JokeDTO();
        dto.setFlagIds(new ArrayList<>());                  // garantía de lista no nula
        model.addAttribute("joke", dto);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("languages", languageService.findAll());
        model.addAttribute("allFlags", flagService.findAll());
        return "jokes/form";
    }

    @GetMapping("/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("joke", jokeService.findById(id)); // aquí toDto ya ha llenado flagIds
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("languages", languageService.findAll());
        model.addAttribute("allFlags", flagService.findAll());
        return "jokes/form";
    }

    @PostMapping
    public String create(
            @ModelAttribute("joke") JokeDTO joke,
            @RequestParam(value = "flagIds", required = false) List<Long> flagIds
    ) {
        // si no marcó ninguno, aseguramos lista vacía
        joke.setFlagIds(flagIds != null ? flagIds : new ArrayList<>());
        jokeService.create(joke);
        return "redirect:/jokes";
    }

    @PostMapping("/{id}")
    public String update(
            @PathVariable Long id,
            @ModelAttribute("joke") JokeDTO joke,
            @RequestParam(value = "flagIds", required = false) List<Long> flagIds
    ) {
        joke.setFlagIds(flagIds != null ? flagIds : new ArrayList<>());
        jokeService.update(id, joke);
        return "redirect:/jokes";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        jokeService.delete(id);
        return "redirect:/jokes";
    }

    @GetMapping("/with-pv")
    public String listWithPV(@RequestParam(required=false) String q, Model model) {
        List<JokeDTO> jokes = jokeService.findAllWithPV();
        if (q != null && !q.isBlank()) {
            String lower = q.toLowerCase();
            jokes = jokes.stream()
                    .filter(j-> j.getText1().toLowerCase().contains(lower))
                    .collect(Collectors.toList());
        }
        model.addAttribute("jokes", jokes);
        model.addAttribute("q", q);
        return "jokes/list-with-pv";
    }
}
