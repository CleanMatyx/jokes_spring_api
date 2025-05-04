package com.matiasborra.jokes.controller.api;

import com.matiasborra.jokes.dto.CategoryDTO;
import com.matiasborra.jokes.model.services.ICategoryService;
import com.matiasborra.jokes.utils.ResponseHelper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las categorías.
 * Proporciona endpoints para operaciones CRUD.
 *
 * @author Matias Borra
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    private final ICategoryService service;

    /**
     * Constructor que inyecta el servicio de categorías.
     *
     * @param service Servicio de categorías
     */
    public CategoryRestController(ICategoryService service) {
        this.service = service;
    }

    /**
     * Obtiene la lista de todas las categorías.
     *
     * @return Lista de categorías
     */
    @GetMapping
    public List<CategoryDTO> list() {
        return service.findAll();
    }

    /**
     * Obtiene una categoría por su ID.
     *
     * @param id ID de la categoría
     * @return Respuesta con la categoría encontrada o un error si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Categoría con Id: " + id + " no encontrada",
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Crea una nueva categoría.
     *
     * @param dto Datos de la categoría a crear
     * @return Respuesta con la categoría creada o un error si falla
     */
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CategoryDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Error al crear la categoría: " + dto.getCategory(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Actualiza una categoría existente.
     *
     * @param id ID de la categoría a actualizar
     * @param dto Datos actualizados de la categoría
     * @return Respuesta con la categoría actualizada o un error si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody CategoryDTO dto) {
        try {
            return ResponseEntity.ok(service.update(id, dto));
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Categoría con Id: " + id + " no encontrada",
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina una categoría por su ID.
     *
     * @param id ID de la categoría a eliminar
     * @return Respuesta sin contenido o un error si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Categoría con Id: " + id + " no encontrada",
                    HttpStatus.NOT_FOUND);
        }
    }
}