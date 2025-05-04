package com.matiasborra.jokes.controller.web;

import com.matiasborra.jokes.dto.CategoryDTO;
import com.matiasborra.jokes.model.services.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador web para gestionar las categorías.
 * Proporciona vistas para operaciones CRUD.
 *
 * @author Matias Borra
 */
@Controller
@RequestMapping("/categories")
public class CategoryWebController {

    private final ICategoryService categoryService;

    /**
     * Constructor que inyecta el servicio de categorías.
     *
     * @param categoryService Servicio de categorías
     */
    public CategoryWebController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Muestra la lista de todas las categorías.
     *
     * @param model Modelo para pasar datos a la vista
     * @return Nombre de la vista de lista de categorías
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories/list";
    }

    /**
     * Muestra el formulario para crear una nueva categoría.
     *
     * @param model Modelo para pasar datos a la vista
     * @return Nombre de la vista del formulario de creación
     */
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("category", new CategoryDTO());
        return "categories/form";
    }

    /**
     * Crea una nueva categoría.
     *
     * @param category Objeto CategoryDTO con los datos de la categoría
     * @param br Resultado de la validación
     * @return Redirección a la lista de categorías o vista del formulario si hay errores
     */
    @PostMapping
    public String create(@Valid @ModelAttribute("category") CategoryDTO category, BindingResult br) {
        if (br.hasErrors()) {
            return "categories/form";
        }
        categoryService.create(category);
        return "redirect:/categories";
    }

    /**
     * Muestra el formulario para editar una categoría existente.
     *
     * @param id ID de la categoría a editar
     * @param model Modelo para pasar datos a la vista
     * @return Nombre de la vista del formulario de edición
     */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        return "categories/form";
    }

    /**
     * Actualiza una categoría existente.
     *
     * @param id ID de la categoría a actualizar
     * @param category Objeto CategoryDTO con los datos actualizados
     * @param br Resultado de la validación
     * @return Redirección a la lista de categorías o vista del formulario si hay errores
     */
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("category") CategoryDTO category,
                         BindingResult br) {
        if (br.hasErrors()) {
            return "categories/form";
        }
        categoryService.update(id, category);
        return "redirect:/categories";
    }

    /**
     * Elimina una categoría por su ID.
     *
     * @param id ID de la categoría a eliminar
     * @return Redirección a la lista de categorías
     */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}