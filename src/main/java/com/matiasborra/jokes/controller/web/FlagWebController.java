package com.matiasborra.jokes.controller.web;

import com.matiasborra.jokes.dto.FlagDTO;
import com.matiasborra.jokes.model.services.IFlagService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador web para gestionar las banderas (flags).
 * Proporciona vistas para operaciones CRUD.
 *
 * @author Matias Borra
 */
@Controller
@RequestMapping("/flags")
public class FlagWebController {

    private final IFlagService flagService;

    /**
     * Constructor que inyecta el servicio de banderas.
     *
     * @param flagService Servicio de banderas
     */
    public FlagWebController(IFlagService flagService) {
        this.flagService = flagService;
    }

    /**
     * Muestra el listado de todas las banderas.
     *
     * @param model Modelo para pasar datos a la vista
     * @return Nombre de la vista de listado de banderas
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("flags", flagService.findAll());
        return "flags/list";
    }

    /**
     * Muestra el formulario para crear una nueva bandera.
     *
     * @param model Modelo para pasar datos a la vista
     * @return Nombre de la vista del formulario de creación
     */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("flag", new FlagDTO());
        return "flags/form";
    }

    /**
     * Procesa la creación de una nueva bandera.
     *
     * @param flag  Objeto FlagDTO con los datos de la bandera
     * @param result Resultado de la validación
     * @param attrs Atributos para redirección
     * @return Redirección al listado de banderas o vista del formulario si hay errores
     */
    @PostMapping
    public String create(@Valid @ModelAttribute("flag") FlagDTO flag,
                         BindingResult result, RedirectAttributes attrs
    ) {
        if (result.hasErrors()) {
            return "flags/form";
        }
        flagService.create(flag);
        attrs.addFlashAttribute("success", "Flag creada correctamente");
        return "redirect:/flags";
    }

    /**
     * Muestra el formulario para editar una bandera existente.
     *
     * @param id ID de la bandera a editar
     * @param model Modelo para pasar datos a la vista
     * @return Nombre de la vista del formulario de edición
     */
    @GetMapping("/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        FlagDTO dto = flagService.findById(id);
        model.addAttribute("flag", dto);
        return "flags/form";
    }

    /**
     * Procesa la edición de una bandera existente.
     *
     * @param id ID de la bandera a actualizar
     * @param flag Objeto FlagDTO con los datos actualizados
     * @param result Resultado de la validación
     * @param attrs Atributos para redirección
     * @return Redirección al listado de banderas o vista del formulario si hay errores
     */
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("flag") FlagDTO flag,
                         BindingResult result, RedirectAttributes attrs
    ) {
        if (result.hasErrors()) {
            return "flags/form";
        }
        flagService.update(id, flag);
        attrs.addFlashAttribute("success", "Flag actualizada correctamente");
        return "redirect:/flags";
    }

    /**
     * Elimina una bandera por su ID.
     *
     * @param id ID de la bandera a eliminar
     * @param attrs Atributos para redirección
     * @return Redirección al listado de banderas
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attrs) {
        flagService.delete(id);
        attrs.addFlashAttribute("success", "Flag borrada correctamente");
        return "redirect:/flags";
    }
}