package com.matiasborra.jokes.controller.api;

import com.matiasborra.jokes.dto.LanguageDTO;
import com.matiasborra.jokes.model.services.ILanguageService;
import com.matiasborra.jokes.utils.ResponseHelper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar los idiomas.
 * Proporciona endpoints para operaciones CRUD.
 *
 * @author Matias Borra
 */
@RestController
@RequestMapping("/api/language")
public class LanguageRestController {

    private final ILanguageService service;

    /**
     * Constructor que inyecta el servicio de idiomas.
     *
     * @param service Servicio de idiomas
     */
    public LanguageRestController(ILanguageService service) {
        this.service = service;
    }

    /**
     * Obtiene la lista de todos los idiomas.
     *
     * @return Lista de idiomas
     */
    @GetMapping
    public List<LanguageDTO> list() {
        return service.findAll();
    }

    /**
     * Obtiene un idioma por su ID.
     *
     * @param id ID del idioma
     * @return Respuesta con el idioma encontrado o un error si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Idioma con Id: " + id + " no encontrado",
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Crea un nuevo idioma.
     *
     * @param dto Datos del idioma a crear
     * @return Respuesta con el idioma creado o un error si falla
     */
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody LanguageDTO dto) {
        try {
            return ResponseEntity.ok(service.create(dto));
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Error al crear el idioma",
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Actualiza un idioma existente.
     *
     * @param id  ID del idioma a actualizar
     * @param dto Datos actualizados del idioma
     * @return Respuesta con el idioma actualizado o un error si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody LanguageDTO dto) {
        try {
            return ResponseEntity.ok(service.update(id, dto));
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Idioma con Id: " + id + " no encontrado",
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un idioma por su ID.
     *
     * @param id ID del idioma a eliminar
     * @return Respuesta sin contenido o un error si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Idioma con Id: " + id + " no encontrado",
                    HttpStatus.NOT_FOUND);
        }
    }
}