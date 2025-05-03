package com.matiasborra.jokes.controller.api;

import com.matiasborra.jokes.dto.JokeDTO;
import com.matiasborra.jokes.model.services.IJokeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jokes")
public class JokeRestController {

    private final IJokeService jokeService;

    public JokeRestController(IJokeService jokeService) {
        this.jokeService = jokeService;
    }

    @GetMapping
    public ResponseEntity<List<JokeDTO>> getAll() {
        return ResponseEntity.ok(jokeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JokeDTO> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(jokeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<JokeDTO> create(@Valid @RequestBody JokeDTO dto) {
        JokeDTO created = jokeService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JokeDTO> update(@PathVariable Long id,
                                          @Valid @RequestBody JokeDTO dto) {
        return ResponseEntity.ok(jokeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jokeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/with-pv")
    public ResponseEntity<List<JokeDTO>> getAllWithPrimeraVez() {
        return ResponseEntity.ok(jokeService.findAllWithPV());
    }
}
