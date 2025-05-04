package com.matiasborra.jokes.controller.api;

import com.matiasborra.jokes.dto.JokeDTO;
import com.matiasborra.jokes.model.services.IJokeService;
import com.matiasborra.jokes.utils.ResponseHelper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar los chistes.
 * Proporciona endpoints para operaciones CRUD.
 *
 * @author Matias Borra
 */
@RestController
@RequestMapping("/api/jokes")
public class JokeRestController {

    private final IJokeService jokeService;

    /**
     * Constructor que inyecta el servicio de chistes.
     *
     * @param jokeService Servicio de chistes
     */
    public JokeRestController(IJokeService jokeService) {
        this.jokeService = jokeService;
    }

    /**
     * Obtiene la lista de todos los chistes.
     *
     * @return Respuesta con la lista de chistes
     */
    @GetMapping
    public ResponseEntity<List<JokeDTO>> getAll() {
        return ResponseEntity.ok(jokeService.findAll());
    }

    /**
     * Obtiene un chiste por su ID.
     *
     * @param id ID del chiste
     * @return Respuesta con el chiste encontrado o un error si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(jokeService.findById(id));
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Chiste con Id: " + id + " no encontrado",
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Crea un nuevo chiste.
     *
     * @param dto Datos del chiste a crear
     * @return Respuesta con el chiste creado o un error si falla
     */
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody JokeDTO dto) {
        try {
            JokeDTO created = jokeService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Error al crear el chiste",
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Actualiza un chiste existente.
     *
     * @param id  ID del chiste a actualizar
     * @param dto Datos actualizados del chiste
     * @return Respuesta con el chiste actualizado o un error si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody JokeDTO dto) {
        try {
            return ResponseEntity.ok(jokeService.update(id, dto));
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Chiste con Id: " + id + " no encontrado",
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un chiste por su ID.
     *
     * @param id ID del chiste a eliminar
     * @return Respuesta sin contenido o un error si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            jokeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Chiste con Id: " + id + " no encontrado",
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtiene la lista de todos los chistes con "Primera Vez".
     *
     * @return Respuesta con la lista de chistes con "Primera Vez"
     */
    @GetMapping("/with-pv")
    public ResponseEntity<List<JokeDTO>> getAllWithPrimeraVez() {
        return ResponseEntity.ok(jokeService.findAllWithPV());
    }
}