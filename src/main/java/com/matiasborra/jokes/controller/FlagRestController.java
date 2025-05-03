package com.matiasborra.jokes.controller;

import com.matiasborra.jokes.dto.FlagDTO;
import com.matiasborra.jokes.model.services.IFlagService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flags")
public class FlagRestController {

    private final IFlagService flagService;

    public FlagRestController(IFlagService flagService) {
        this.flagService = flagService;
    }

    @GetMapping
    public List<FlagDTO> getAll() {
        return flagService.findAll();
    }

    @GetMapping("/{id}")
    public FlagDTO getOne(@PathVariable Long id) {
        return flagService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlagDTO create(@Valid @RequestBody FlagDTO dto) {
        return flagService.create(dto);
    }

    @PutMapping("/{id}")
    public FlagDTO update(@PathVariable Long id, @RequestBody FlagDTO dto) {
        return flagService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        flagService.delete(id);
    }
}
