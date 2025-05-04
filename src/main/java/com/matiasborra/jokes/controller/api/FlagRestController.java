package com.matiasborra.jokes.controller.api;

import com.matiasborra.jokes.dto.FlagDTO;
import com.matiasborra.jokes.model.projections.FlagJokeProjection;
import com.matiasborra.jokes.model.services.IFlagService;
import com.matiasborra.jokes.model.services.IJokeService;
import com.matiasborra.jokes.utils.ResponseHelper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para gestionar los flags.
 * Proporciona endpoints para operaciones CRUD.
 *
 * @author Matias Borra
 */
@RestController
@RequestMapping("/api/flags")
public class FlagRestController {

    private final IFlagService flagService;
    private final IJokeService jokeService;

    /**
     * Constructor que inyecta el servicio de flags.
     *
     * @param flagService Servicio de flags
     */
    public FlagRestController(IFlagService flagService, IJokeService jokeService) {
        this.flagService = flagService;
        this.jokeService = jokeService;
    }

    /**
     * Obtiene la lista de todos los flags.
     *
     * @return Lista de flags
     */
    @GetMapping
    public List<FlagDTO> getAll() {
        return flagService.findAll();
    }


    /**
     * Obtiene un flag por su ID.
     *
     * @param id ID del flag
     * @return Respuesta con el flag encontrado o un error si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(flagService.findById(id));
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Flag con Id: " + id + " no encontrada",
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Crea un nuevo flag.
     *
     * @param dto Datos del flag a crear
     * @return Respuesta con el flag creado o un error si falla
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> create(@Valid @RequestBody FlagDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(flagService.create(dto));
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Error al crear el flag",
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Actualiza un flag existente.
     *
     * @param id  ID del flag a actualizar
     * @param dto Datos actualizados del flag
     * @return Respuesta con el flag actualizado o un error si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody FlagDTO dto) {
        try {
            return ResponseEntity.ok(flagService.update(id, dto));
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Flag con Id: " + id + " no encontrada",
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un flag por su ID.
     *
     * @param id ID del flag a eliminar
     * @return Respuesta sin contenido o un error si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            flagService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Flag con Id: " + id + " error al eliminar" + e.getMessage(),
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/jokes/{flagId}")
    public ResponseEntity<Object> getJokesByFlag(@PathVariable Long flagId) {
        try {
            // 1) Carga el flag (solo para obtener id y nombre)
            var flagDto = flagService.findById(flagId);

            // 2) Lista las proyecciones
            var jokes = flagService.listarPorFlag(flagId);

            if (jokes.isEmpty()) {
                // si prefieres un 204 sin cuerpo:
                return ResponseHelper.createErrorResponse(
                        "No hay chistes para el flag " + flagId,
                        HttpStatus.NO_CONTENT);
            }

            // 3) Construye el payload combinando flag + lista
            Map<String,Object> payload = Map.of(
                    "flagId",   flagDto.getId(),
                    "flagName", flagDto.getFlag(),
                    "jokes",    jokes
            );

            // 4) Devuelve con wrapper
            return ResponseHelper.createSuccessResponse(payload);

        } catch (RuntimeException e) {
            return ResponseHelper.createErrorResponse(
                    "Flag con Id: " + flagId + " no encontrada",
                    HttpStatus.NOT_FOUND);
        }
    }
}