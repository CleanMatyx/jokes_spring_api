package com.matiasborra.jokes.controller.web;

import com.matiasborra.jokes.dto.FlagDTO;
import com.matiasborra.jokes.model.services.IFlagService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/flags")
public class FlagWebController {

    private final IFlagService flagService;

    public FlagWebController(IFlagService flagService) {
        this.flagService = flagService;
    }

    /** Mostrar listado de flags **/
    @GetMapping
    public String list(Model model) {
        model.addAttribute("flags", flagService.findAll());
        return "flags/list";
    }

    /** Form para crear **/
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("flag", new FlagDTO());
        return "flags/form";
    }

    /** Procesar creación **/
    @PostMapping
    public String create(
            @Valid @ModelAttribute("flag") FlagDTO flag,
            BindingResult result,
            RedirectAttributes attrs
    ) {
        if (result.hasErrors()) {
            return "flags/form";
        }
        flagService.create(flag);
        attrs.addFlashAttribute("success", "Flag creada correctamente");
        return "redirect:/flags";
    }

    /** Form para editar **/
    @GetMapping("/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        FlagDTO dto = flagService.findById(id);
        model.addAttribute("flag", dto);
        return "flags/form";
    }

    /** Procesar edición **/
    @PostMapping("/{id}")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("flag") FlagDTO flag,
            BindingResult result,
            RedirectAttributes attrs
    ) {
        if (result.hasErrors()) {
            return "flags/form";
        }
        flagService.update(id, flag);
        attrs.addFlashAttribute("success", "Flag actualizada correctamente");
        return "redirect:/flags";
    }

    /** Borrar **/
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attrs) {
        flagService.delete(id);
        attrs.addFlashAttribute("success", "Flag borrada correctamente");
        return "redirect:/flags";
    }
}
