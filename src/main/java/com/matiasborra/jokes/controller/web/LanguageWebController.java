package com.matiasborra.jokes.controller.web;

import com.matiasborra.jokes.dto.LanguageDTO;
import com.matiasborra.jokes.model.services.ILanguageService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador web para gestionar los idiomas.
 * Proporciona vistas para operaciones CRUD.
 *
 * @author Matias Borra
 */
@Controller
@RequestMapping("/languages")
public class LanguageWebController {

    private final ILanguageService languageService;

    /**
     * Constructor que inyecta el servicio de idiomas
     *
     * @param languageService Servicio de idiomas
     */
    public LanguageWebController(ILanguageService languageService) {
        this.languageService = languageService;
    }

    /**
     * Muestra el listado de todos los idiomas.
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("languages", languageService.findAll());
        return "languages/list";
    }

    /**
     * Muestra el formulario para crear un nuevo idioma.
     */
    @GetMapping("/new")
    public String createForm(Model model) {
        // Usamos LanguageDTO para que coincida con el @ModelAttribute de los POST
        model.addAttribute("language", new LanguageDTO());
        return "languages/form";
    }

    /**
     * Procesa la creación de un nuevo idioma.
     */
    @PostMapping
    public String create(
            @Valid @ModelAttribute("language") LanguageDTO language,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "languages/form";
        }
        languageService.create(language);
        return "redirect:/languages";
    }

    /**
     * Muestra el formulario para editar un idioma existente.
     */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        // languageService.findById devuelve un LanguageDTO
        model.addAttribute("language", languageService.findById(id));
        return "languages/form";
    }

    /**
     * Procesa la actualización de un idioma existente.
     */
    @PostMapping("/{id}")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("language") LanguageDTO language,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "languages/form";
        }
        languageService.update(id, language);
        return "redirect:/languages";
    }

    /**
     * Elimina un idioma por su ID.
     */
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        languageService.delete(id);
        return "redirect:/languages";
    }
}
