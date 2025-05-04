package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.FlagDTO;
import com.matiasborra.jokes.model.projections.FlagJokeProjection;

import java.util.List;

/**
 * Interfaz IFlagService.
 * Define los métodos para gestionar las banderas, incluyendo operaciones CRUD y consultas personalizadas.
 *
 * @author Matias Borra
 */
public interface IFlagService {

    /**
     * Obtiene todas las banderas.
     *
     * @return Lista de banderas en formato DTO
     */
    List<FlagDTO> findAll();

    /**
     * Busca una bandera por su ID.
     *
     * @param id ID de la bandera
     * @return Bandera en formato DTO
     */
    FlagDTO findById(Long id);

    /**
     * Crea una nueva bandera.
     *
     * @param dto DTO de la bandera a crear
     * @return Bandera creada en formato DTO
     */
    FlagDTO create(FlagDTO dto);

    /**
     * Actualiza una bandera existente.
     *
     * @param id  ID de la bandera a actualizar
     * @param dto DTO con los datos actualizados
     * @return Bandera actualizada en formato DTO
     */
    FlagDTO update(Long id, FlagDTO dto);

    /**
     * Elimina una bandera por su ID.
     *
     * @param id ID de la bandera a eliminar
     */
    void delete(Long id);

    /**
     * Lista los chistes asociados a una bandera específica.
     *
     * @param flagId ID de la bandera
     * @return Lista de proyecciones de chistes asociados a la bandera
     */
    List<FlagJokeProjection> listarPorFlag(Long flagId);
}