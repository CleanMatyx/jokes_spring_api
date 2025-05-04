package com.matiasborra.jokes.controller.api;

import com.matiasborra.jokes.dto.TypeDTO;
import com.matiasborra.jokes.model.services.ITypeService;
import com.matiasborra.jokes.utils.ResponseHelper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar los tipos.
 * Proporciona endpoints para operaciones CRUD.
 *
 * @author Matias Borra
 */
@RestController
@RequestMapping("/api/types")
public class TypeRestController {

    private final ITypeService service;

    /**
     * Constructor que inyecta el servicio de tipos.
     *
     * @param service Servicio de tipos
     */
    public TypeRestController(ITypeService service) {
        this.service = service;
    }

    /**
     * Obtiene la lista de todos los tipos.
     *
     * @return Lista de tipos
     */
    @GetMapping
    public List<TypeDTO> list() {
        return service.findAll();
    }

    /**
     * Obtiene un tipo por su ID.
     *
     * @param id ID del tipo
     * @return Respuesta con el tipo encontrado o un error si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Tipo con Id: " + id + " no encontrado",
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Crea un nuevo tipo.
     *
     * @param dto Datos del tipo a crear
     * @return Respuesta con el tipo creado o un error si falla
     */
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody TypeDTO dto) {
        try {
            return ResponseEntity.ok(service.create(dto));
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Error al crear el tipo",
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Actualiza un tipo existente.
     *
     * @param id  ID del tipo a actualizar
     * @param dto Datos actualizados del tipo
     * @return Respuesta con el tipo actualizado o un error si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody TypeDTO dto) {
        try {
            return ResponseEntity.ok(service.update(id, dto));
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Tipo con Id: " + id + " no encontrado",
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un tipo por su ID.
     *
     * @param id ID del tipo a eliminar
     * @return Respuesta sin contenido o un error si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Tipo con Id: " + id + " no encontrado",
                    HttpStatus.NOT_FOUND);
        }
    }
}