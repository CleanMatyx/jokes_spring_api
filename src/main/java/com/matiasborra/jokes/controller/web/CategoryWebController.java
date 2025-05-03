package com.matiasborra.jokes.controller.web;

import com.matiasborra.jokes.dto.CategoryDTO;
import com.matiasborra.jokes.model.services.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryWebController {

    private final ICategoryService categoryService;

    public CategoryWebController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("category", new CategoryDTO());
        return "categories/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("category") CategoryDTO category,
                         BindingResult br) {
        if (br.hasErrors()) {
            return "categories/form";
        }
        categoryService.create(category);
        return "redirect:/categories";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        return "categories/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("category") CategoryDTO category,
                         BindingResult br) {
        if (br.hasErrors()) {
            return "categories/form";
        }
        categoryService.update(id, category);
        return "redirect:/categories";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}
