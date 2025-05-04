package com.matiasborra.jokes.controller.web;

import com.matiasborra.jokes.dto.TypeDTO;
import com.matiasborra.jokes.model.entity.Type;
import com.matiasborra.jokes.model.services.ITypeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador web para gestionar los tipos.
 * Proporciona vistas para operaciones CRUD.
 *
 * @author Matias Borra
 */
@Controller
@RequestMapping("/types")
public class TypeWebController {

    private final ITypeService typeService;

    /**
     * Constructor que inyecta el servicio de tipos.
     *
     * @param typeService Servicio de tipos
     */
    public TypeWebController(ITypeService typeService) {
        this.typeService = typeService;
    }

    /**
     * Muestra el listado de todos los tipos.
     *
     * @param model Modelo para pasar datos a la vista
     * @return Nombre de la vista de listado de tipos
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("types", typeService.findAll());
        return "types/list";
    }

    /**
     * Muestra el formulario para crear un nuevo tipo.
     *
     * @param model Modelo para pasar datos a la vista
     * @return Nombre de la vista del formulario de creación
     */
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("type", new Type());
        return "types/form";
    }

    /**
     * Procesa la creación de un nuevo tipo.
     *
     * @param type Objeto TypeDTO con los datos del tipo
     * @param result Resultado de la validación
     * @return Redirección al listado de tipos o vista del formulario si hay errores
     */
    @PostMapping
    public String save(
            @Valid @ModelAttribute("type") TypeDTO type,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "types/form";
        }
        typeService.create(type);
        return "redirect:/types";
    }

    /**
     * Muestra el formulario para editar un tipo existente.
     *
     * @param id ID del tipo a editar
     * @param model Modelo para pasar datos a la vista
     * @return Nombre de la vista del formulario de edición
     */
    @GetMapping("/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.findById(id));
        return "types/form";
    }

    /**
     * Procesa la actualización de un tipo existente.
     *
     * @param id ID del tipo a actualizar
     * @param type Objeto TypeDTO con los datos actualizados
     * @param result Resultado de la validación
     * @return Redirección al listado de tipos o vista del formulario si hay errores
     */
    @PostMapping("/{id}")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("type") TypeDTO type,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "types/form";
        }
        typeService.update(id, type);
        return "redirect:/types";
    }

    /**
     * Elimina un tipo por su ID.
     *
     * @param id ID del tipo a eliminar
     * @return Redirección al listado de tipos
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        typeService.delete(id);
        return "redirect:/types";
    }
}