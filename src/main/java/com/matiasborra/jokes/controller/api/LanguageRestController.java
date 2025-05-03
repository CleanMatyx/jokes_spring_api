package com.matiasborra.jokes.controller.api;

import com.matiasborra.jokes.dto.LanguageDTO;
import com.matiasborra.jokes.model.services.ILanguageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/language")
public class LanguageRestController {

    private final ILanguageService service;
    public LanguageRestController(ILanguageService service) {
        this.service = service;
    }

    @GetMapping
    public List<LanguageDTO> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public LanguageDTO get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<LanguageDTO> create(@Valid @RequestBody LanguageDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public LanguageDTO update(@PathVariable Long id,
                              @Valid @RequestBody LanguageDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}