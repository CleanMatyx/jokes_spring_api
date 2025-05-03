package com.matiasborra.jokes.controller.web;

import com.matiasborra.jokes.dto.PrimeraVezDTO;
import com.matiasborra.jokes.dto.TelefonoDTO;
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

//    /** Formulario para dar de alta la primera vez de un chiste */
//    @GetMapping("/new/{jokeId}")
//    public String newForm(@PathVariable Long jokeId, Model model) {
//        // Inicializamos el DTO con el jokeId para que el form lo conserve
//        PrimeraVezDTO pv = new PrimeraVezDTO();
//        pv.setJokeId(jokeId);
//
//        model.addAttribute("primeraVez", pv);
//        model.addAttribute("joke", jokeService.findById(jokeId));
//        return "primera_vez/form";
//    }

    @GetMapping("/new/{jokeId}")
    public String newForm(@PathVariable Long jokeId, Model model) {
        PrimeraVezDTO dto = new PrimeraVezDTO();
        dto.setJokeId(jokeId);
        // Añado un teléfono vacío para que Thymeleaf muestre al menos un input
        dto.getTelefonos().add(new TelefonoDTO());
        model.addAttribute("primeraVez", dto);
        model.addAttribute("joke", jokeService.findById(jokeId));
        return "primera_vez/form";
    }

//    /** Formulario de edición: si ya existe, lo recuperamos; si no, redirigimos a “new” */
//    @GetMapping("/edit/{jokeId}")
//    public String editForm(@PathVariable Long jokeId, Model model) {
//        PrimeraVezDTO pv = pvService.findByJokeId(jokeId);
//        if (pv == null) {
//            // no existe → vamos al alta
//            return "redirect:/primera_vez/new/" + jokeId;
//        }
//        model.addAttribute("primeraVez", pv);
//        model.addAttribute("joke", jokeService.findById(jokeId));
//        return "primera_vez/form";
//    }

    @GetMapping("/edit/{jokeId}")
    public String editForm(@PathVariable Long jokeId, Model model) {
        PrimeraVezDTO dto = pvService.findByJokeId(jokeId);
        if (dto == null) {
            return "redirect:/primera_vez/new/" + jokeId;
        }
        model.addAttribute("primeraVez", dto);
        model.addAttribute("joke", jokeService.findById(jokeId));
        return "primera_vez/form";
    }

    /**
     * Almacenamiento (tanto para creación como para actualización).
     * El DTO lleva el campo jokeId en todo momento.
     */
    @PostMapping("/save")
    public String save(@ModelAttribute("primeraVez") PrimeraVezDTO dto) {
        pvService.save(dto);
        return "redirect:/jokes";
    }

    /** Borrado por jokeId */
    @GetMapping("/delete/{jokeId}")
    public String delete(@PathVariable Long jokeId) {
        pvService.deleteByJokeId(jokeId);
        return "redirect:/jokes";
    }
}
