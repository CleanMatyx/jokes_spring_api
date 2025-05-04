package com.matiasborra.jokes.controller.web;

import com.matiasborra.jokes.dto.JokeDTO;
import com.matiasborra.jokes.model.projections.FlagJokeProjection;
import com.matiasborra.jokes.model.services.ICategoryService;
import com.matiasborra.jokes.model.services.IFlagService;
import com.matiasborra.jokes.model.services.ILanguageService;
import com.matiasborra.jokes.model.services.IJokeService;
import com.matiasborra.jokes.model.services.ITypeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador web para gestionar los chistes.
 * Proporciona vistas para operaciones CRUD y búsquedas específicas.
 *
 * @author Matias Borra
 */
@Controller
@RequestMapping("/jokes")
public class JokeWebController {

    private final IJokeService jokeService;
    private final ICategoryService categoryService;
    private final ITypeService typeService;
    private final ILanguageService languageService;
    private final IFlagService flagService;

    /**
     * Constructor que inyecta los servicios necesarios.
     *
     * @param jokeService Servicio de chistes
     * @param categoryService Servicio de categorías
     * @param typeService Servicio de tipos
     * @param languageService Servicio de idiomas
     * @param flagService Servicio de banderas
     */
    public JokeWebController(IJokeService jokeService, ICategoryService categoryService,
                             ITypeService typeService, ILanguageService languageService,
                             IFlagService flagService) {
        this.jokeService = jokeService;
        this.categoryService = categoryService;
        this.typeService = typeService;
        this.languageService = languageService;
        this.flagService = flagService;
    }

    /**
     * Muestra la lista de chistes, con opción de filtrar por texto.
     *
     * @param q Texto para filtrar los chistes
     * @param model Modelo para pasar datos a la vista
     * @return Nombre de la vista de lista de chistes
     */
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

    /**
     * Muestra el formulario para crear un nuevo chiste.
     *
     * @param model Modelo para pasar datos a la vista
     * @return Nombre de la vista del formulario de creación
     */
    @GetMapping("/new")
    public String newForm(Model model) {
        JokeDTO dto = new JokeDTO();
        dto.setFlagIds(new ArrayList<>());
        model.addAttribute("joke", dto);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("languages", languageService.findAll());
        model.addAttribute("allFlags", flagService.findAll());
        return "jokes/form";
    }

    /**
     * Muestra el formulario para editar un chiste existente.
     *
     * @param id ID del chiste a editar
     * @param model Modelo para pasar datos a la vista
     * @return Nombre de la vista del formulario de edición
     */
    @GetMapping("/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("joke", jokeService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("languages", languageService.findAll());
        model.addAttribute("allFlags", flagService.findAll());
        return "jokes/form";
    }

    /**
     * Procesa la creación de un nuevo chiste.
     *
     * @param joke Objeto JokeDTO con los datos del chiste
     * @param flagIds Lista de IDs de banderas asociadas
     * @return Redirección a la lista de chistes
     */
    @PostMapping
    public String create(@Valid @ModelAttribute("joke") JokeDTO joke,
                         @RequestParam(value = "flagIds", required = false) List<Long> flagIds) {
        joke.setFlagIds(flagIds != null ? flagIds : new ArrayList<>());
        jokeService.create(joke);
        return "redirect:/jokes";
    }

    /**
     * Procesa la actualización de un chiste existente.
     *
     * @param id ID del chiste a actualizar
     * @param joke Objeto JokeDTO con los datos actualizados
     * @param flagIds Lista de IDs de banderas asociadas
     * @return Redirección a la lista de chistes
     */
    @PostMapping("/{id}")
    public String update(@Valid @PathVariable Long id, @ModelAttribute("joke") JokeDTO joke,
                         @RequestParam(value = "flagIds", required = false) List<Long> flagIds) {
        joke.setFlagIds(flagIds != null ? flagIds : new ArrayList<>());
        jokeService.update(id, joke);
        return "redirect:/jokes";
    }

    /**
     * Elimina un chiste por su ID.
     *
     * @param id ID del chiste a eliminar
     * @return Redirección a la lista de chistes
     */
    @PostMapping("/delete/{id}")
    public String deleteJoke(@PathVariable Long id, RedirectAttributes attrs) {
        try {
            jokeService.delete(id);
            attrs.addFlashAttribute("success", "Chiste eliminado correctamente");
        } catch (RuntimeException e) {
            attrs.addFlashAttribute("error", "No se pudo eliminar el chiste: " + e.getMessage());
        }
        return "redirect:/jokes";
    }

    /**
     * Muestra la lista de chistes con información adicional de "Primera Vez".
     *
     * @param q Texto para filtrar los chistes
     * @param model Modelo para pasar datos a la vista
     * @return Nombre de la vista de lista de chistes con "Primera Vez"
     */
    @GetMapping("/with-pv")
    public String listWithPV(@RequestParam(required = false) String q, Model model) {
        List<JokeDTO> jokes = jokeService.findAllWithPV();
        if (q != null && !q.isBlank()) {
            String lower = q.toLowerCase();
            jokes = jokes.stream()
                    .filter(j -> j.getText1().toLowerCase().contains(lower))
                    .collect(Collectors.toList());
        }
        model.addAttribute("jokes", jokes);
        model.addAttribute("q", q);
        return "jokes/list-with-pv";
    }

    @GetMapping("/no-pv")
    public String listWithoutPV(Model model) {
        model.addAttribute("jokes", jokeService.findAllWithoutPV());
        model.addAttribute("q", null);
        return "jokes/list";
    }

}