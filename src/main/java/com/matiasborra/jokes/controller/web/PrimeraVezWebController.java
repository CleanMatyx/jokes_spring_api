package com.matiasborra.jokes.controller.web;

import com.matiasborra.jokes.dto.PrimeraVezDTO;
import com.matiasborra.jokes.model.services.IJokeService;
import com.matiasborra.jokes.model.services.IPrimeraVezService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/primera_vez")
public class PrimeraVezWebController {

    private final IPrimeraVezService pvService;
    private final IJokeService jokeService;

    public PrimeraVezWebController(
            IPrimeraVezService pvService,
            IJokeService jokeService
    ) {
        this.pvService   = pvService;
        this.jokeService = jokeService;
    }

    @GetMapping("/new/{jokeId}")
    public String newForm(@PathVariable Long jokeId, Model model) {
        model.addAttribute("primeraVez", new PrimeraVezDTO());
        model.addAttribute("joke", jokeService.findById(jokeId));
        return "primera_vez/form";
    }

    @GetMapping("/edit/{jokeId}")
    public String editForm(@PathVariable Long jokeId, Model model) {
        PrimeraVezDTO pv = pvService.findByJokeId(jokeId);
        if (pv == null) {
            return "redirect:/primera_vez/new/" + jokeId;
        }
        model.addAttribute("primeraVez", pv);
        model.addAttribute("joke", jokeService.findById(jokeId));
        return "primera_vez/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("primeraVez") PrimeraVezDTO dto) {
        pvService.save(dto);
        return "redirect:/jokes";
    }

    @GetMapping("/delete/{jokeId}")
    public String delete(@PathVariable Long jokeId) {
        pvService.deleteByJokeId(jokeId);
        return "redirect:/jokes";
    }
}
