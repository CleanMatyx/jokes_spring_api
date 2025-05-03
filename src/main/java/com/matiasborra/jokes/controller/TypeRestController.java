package com.matiasborra.jokes.controller;

import com.matiasborra.jokes.dto.TypeDTO;
import com.matiasborra.jokes.model.services.ITypeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
public class TypeRestController {

    private final ITypeService service;
    public TypeRestController(ITypeService service) {
        this.service = service;
    }

    @GetMapping
    public List<TypeDTO> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public TypeDTO get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<TypeDTO> create(@Valid @RequestBody TypeDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public TypeDTO update(@PathVariable Long id,
                          @Valid @RequestBody TypeDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}