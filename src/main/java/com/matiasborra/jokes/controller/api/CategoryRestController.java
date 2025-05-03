package com.matiasborra.jokes.controller.api;

import com.matiasborra.jokes.dto.CategoryDTO;
import com.matiasborra.jokes.model.services.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    private final ICategoryService service;
    public CategoryRestController(ICategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoryDTO> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDTO get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public CategoryDTO update(@PathVariable Long id,
                              @Valid @RequestBody CategoryDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}