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

    /** Listado de todas las primeras emisiones **/
    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("entries", pvService.findAll());
        return "primera_vez/list";
    }

    /** Formulario para crear una nueva emisión para un chiste dado **/
    @GetMapping("/new/{jokeId}")
    public String newForm(@PathVariable Long jokeId, Model model) {
        PrimeraVezDTO pv = new PrimeraVezDTO();
        pv.setJokeId(jokeId);
        model.addAttribute("pv", pv);
        model.addAttribute("joke", jokeService.findById(jokeId));
        return "primera_vez/form";
    }

    /** Procesa el guardado de una nueva emisión **/
    @PostMapping("/new/{jokeId}")
    public String create(
            @PathVariable Long jokeId,
            @ModelAttribute("pv") PrimeraVezDTO dto
    ) {
        dto.setJokeId(jokeId);
        pvService.save(dto);
        return "redirect:/primera_vez";
    }

    /** Formulario para editar una emisión existente **/
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        PrimeraVezDTO pv = pvService.findById(id);
        if (pv == null) {
            return "redirect:/primera_vez";
        }
        model.addAttribute("pv", pv);
        model.addAttribute("joke", jokeService.findById(pv.getJokeId()));
        return "primera_vez/form";
    }

    /** Procesa la actualización de una emisión **/
    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable Long id,
            @ModelAttribute("pv") PrimeraVezDTO dto
    ) {
        dto.setId(id);
        pvService.save(dto);
        return "redirect:/primera_vez";
    }

    /** Borra una emisión por su ID **/
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        pvService.delete(id);
        return "redirect:/primera_vez";
    }
}
