package com.matiasborra.jokes.controller.web;

import com.matiasborra.jokes.dto.PrimeraVezDTO;
import com.matiasborra.jokes.dto.TelefonoDTO;
import com.matiasborra.jokes.model.services.IJokeService;
import com.matiasborra.jokes.model.services.IPrimeraVezService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador web para gestionar las operaciones relacionadas con "Primera Vez".
 * Proporciona vistas para crear, editar, guardar y eliminar entidades.
 *
 * @author Matias Borra
 */
@Controller
@RequestMapping("/primera_vez")
public class PrimeraVezWebController {

    private final IPrimeraVezService pvService;
    private final IJokeService jokeService;

    /**
     * Constructor que inyecta los servicios necesarios.
     *
     * @param pvService Servicio para gestionar "Primera Vez"
     * @param jokeService Servicio para gestionar chistes
     */
    public PrimeraVezWebController(IPrimeraVezService pvService, IJokeService jokeService) {
        this.pvService = pvService;
        this.jokeService = jokeService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("entries", pvService.findAll());
        return "primera_vez/list";
    }

    /**
     * Muestra el formulario para crear una nueva entidad "Primera Vez".
     *
     * @param jokeId ID del chiste asociado
     * @param model Modelo para pasar datos a la vista
     * @return Nombre de la vista del formulario
     */
    @GetMapping("/new/{jokeId}")
    public String newForm(@PathVariable Long jokeId, Model model) {
        PrimeraVezDTO dto = new PrimeraVezDTO();
        dto.setJokeId(jokeId);
        dto.getTelefonos().add(new TelefonoDTO());
        model.addAttribute("primeraVez", dto);
        model.addAttribute("joke", jokeService.findById(jokeId));
        return "primera_vez/form";
    }

    /**
     * Muestra el formulario para editar una entidad "Primera Vez".
     * Si no existe, redirige al formulario de creación.
     *
     * @param jokeId ID del chiste asociado
     * @param model Modelo para pasar datos a la vista
     * @return Nombre de la vista del formulario o redirección al formulario de creación
     */
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
     * Procesa el almacenamiento de una entidad "Primera Vez".
     * Puede ser tanto para creación como para actualización.
     *
     * @param dto Objeto PrimeraVezDTO con los datos a guardar
     * @return Redirección al listado de chistes
     */
    @PostMapping("/save")
    public String save(@ModelAttribute("primeraVez") PrimeraVezDTO dto) {
        pvService.save(dto);
        return "redirect:/primera_vez";
    }

    /**
     * Elimina una entidad "Primera Vez" por su JokeId.
     *
     * @param jokeId ID del chiste asociado
     * @return Redirección al listado de chistes
     */
    @GetMapping("/delete/{jokeId}")
    public String delete(@PathVariable Long jokeId) {
        pvService.deleteByJokeId(jokeId);
        return "redirect:/primera_vez";
    }

}