package com.matiasborra.jokes.controller.web;

import com.matiasborra.jokes.dto.LanguageDTO;
import com.matiasborra.jokes.model.entity.Language;
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
     * Constructor que inyecta el servicio de idiomas.
     *
     * @param languageService Servicio de idiomas
     */
    public LanguageWebController(ILanguageService languageService) {
        this.languageService = languageService;
    }

    /**
     * Muestra el listado de todos los idiomas.
     *
     * @param model Modelo para pasar datos a la vista
     * @return Nombre de la vista de listado de idiomas
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("languages", languageService.findAll());
        return "languages/list";
    }

    /**
     * Muestra el formulario para crear un nuevo idioma.
     *
     * @param model Modelo para pasar datos a la vista
     * @return Nombre de la vista del formulario de creación
     */
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("language", new Language());
        return "languages/form";
    }

    /**
     * Procesa la creación de un nuevo idioma.
     *
     * @param language Objeto LanguageDTO con los datos del idioma
     * @param result Resultado de la validación
     * @return Redirección al listado de idiomas o vista del formulario si hay errores
     */
    @PostMapping
    public String create(@Valid @ModelAttribute("language") LanguageDTO language, BindingResult result
    ) {
        if (result.hasErrors()) {
            return "languages/form";
        }
        languageService.create(language);
        return "redirect:/languages";
    }

    /**
     * Muestra el formulario para editar un idioma existente.
     *
     * @param id ID del idioma a editar
     * @param model Modelo para pasar datos a la vista
     * @return Nombre de la vista del formulario de edición
     */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("language", languageService.findById(id));
        return "languages/form";
    }

    /**
     * Procesa la actualización de un idioma existente.
     *
     * @param id ID del idioma a actualizar
     * @param language Objeto LanguageDTO con los datos actualizados
     * @param result Resultado de la validación
     * @return Redirección al listado de idiomas o vista del formulario si hay errores
     */
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("language") LanguageDTO language,
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
     *
     * @param id ID del idioma a eliminar
     * @return Redirección al listado de idiomas
     */
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        languageService.delete(id);
        return "redirect:/languages";
    }
}