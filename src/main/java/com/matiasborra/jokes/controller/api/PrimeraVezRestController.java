package com.matiasborra.jokes.controller.api;

import com.matiasborra.jokes.dto.PrimeraVezDTO;
import com.matiasborra.jokes.model.services.IPrimeraVezService;
import com.matiasborra.jokes.utils.ResponseHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las operaciones relacionadas con "Primera Vez".
 * Proporciona endpoints para CRUD y búsquedas específicas.
 *
 * @author Matias Borra
 */
@RestController
@RequestMapping("/api/primera_vez")
public class PrimeraVezRestController {

    private final IPrimeraVezService primeraVezService;

    /**
     * Constructor que inyecta el servicio de "Primera Vez".
     *
     * @param primeraVezService servicio para manejar la lógica de negocio
     */
    public PrimeraVezRestController(IPrimeraVezService primeraVezService) {
        this.primeraVezService = primeraVezService;
    }

    /**
     * Obtiene una lista de todas las entidades "Primera Vez".
     *
     * @return lista de objetos PrimeraVezDTO
     */
    @GetMapping
    public List<PrimeraVezDTO> listAll() {
        return primeraVezService.findAll();
    }

    /**
     * Obtiene una entidad "Primera Vez" por su ID.
     *
     * @param id ID de la entidad
     * @return ResponseEntity con el objeto PrimeraVezDTO o un error si no se encuentra
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        try {
            PrimeraVezDTO dto = primeraVezService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException ex) {
            return ResponseHelper.createErrorResponse(
                    "Primera vez con ID: " + id + " no encontrada",
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtiene una entidad "Primera Vez" por su JokeId.
     *
     * @param jokeId ID del chiste asociado
     * @return ResponseEntity con el objeto PrimeraVezDTO o un error si no se encuentra
     */
    @GetMapping("/joke/{jokeId}")
    public ResponseEntity<Object> getByJokeId(@PathVariable Long jokeId) {
        try {
            PrimeraVezDTO dto = primeraVezService.findByJokeId(jokeId);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException ex) {
            return ResponseHelper.createErrorResponse(
                    "Primera vez con JokeId: " + jokeId + " no encontrada",
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Crea una nueva entidad "Primera Vez".
     *
     * @param dto objeto PrimeraVezDTO con los datos a guardar
     * @return ResponseEntity con el objeto creado o un error si falla
     */
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody PrimeraVezDTO dto) {
        try {
            PrimeraVezDTO saved = primeraVezService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (RuntimeException ex) {
            return ResponseHelper.createErrorResponse(
                    "Error al crear la primera vez",
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Actualiza una entidad "Primera Vez" por su ID.
     *
     * @param id  ID de la entidad a actualizar
     * @param dto objeto PrimeraVezDTO con los datos actualizados
     * @return ResponseEntity con el objeto actualizado o un error si no se encuentra
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateById(@PathVariable Long id, @RequestBody PrimeraVezDTO dto) {
        try {
            dto.setId(id);
            PrimeraVezDTO updated = primeraVezService.save(dto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseHelper.createErrorResponse(
                    "Primera vez con ID: " + id + " no encontrada",
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Actualiza una entidad "Primera Vez" por su JokeId.
     *
     * @param jokeId ID del chiste asociado
     * @param dto    objeto PrimeraVezDTO con los datos actualizados
     * @return ResponseEntity con el objeto actualizado o un error si no se encuentra
     */
    @PutMapping("/joke/{jokeId}")
    public ResponseEntity<Object> updateByJokeId(@PathVariable Long jokeId, @RequestBody PrimeraVezDTO dto) {
        try {
            dto.setJokeId(jokeId);
            PrimeraVezDTO updated = primeraVezService.save(dto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseHelper.createErrorResponse(
                    "Primera vez con JokeId: " + jokeId + " no encontrada",
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina una entidad "Primera Vez" por su ID.
     *
     * @param id ID de la entidad a eliminar
     * @return ResponseEntity vacío o un error si no se encuentra
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        try {
            primeraVezService.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseHelper.createErrorResponse(
                    "Primera vez con ID: " + id + " no encontrada",
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina una entidad "Primera Vez" por su JokeId.
     *
     * @param jokeId ID del chiste asociado
     * @return ResponseEntity vacío o un error si no se encuentra
     */
    @DeleteMapping("/joke/{jokeId}")
    public ResponseEntity<Object> deleteByJokeId(@PathVariable Long jokeId) {
        try {
            primeraVezService.deleteByJokeId(jokeId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseHelper.createErrorResponse(
                    "Primera vez con JokeId: " + jokeId + " no encontrada",
                    HttpStatus.NOT_FOUND);
        }
    }
}