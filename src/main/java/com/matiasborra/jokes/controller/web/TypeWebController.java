package com.matiasborra.jokes.controller.web;

import com.matiasborra.jokes.dto.TypeDTO;
import com.matiasborra.jokes.model.entity.Type;
import com.matiasborra.jokes.model.services.ITypeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/types")
public class TypeWebController {

    private final ITypeService typeService;

    public TypeWebController(ITypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("types", typeService.findAll());
        return "types/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("type", new Type());
        return "types/form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("type") TypeDTO type,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "types/form";
        }
        typeService.create(type);
        return "redirect:/types";
    }

    @GetMapping("/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.findById(id));
        return "types/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("type") TypeDTO type,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "types/form";
        }
        typeService.update(id, type);
        return "redirect:/types";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        typeService.delete(id);
        return "redirect:/types";
    }
}
