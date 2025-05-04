package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.PrimeraVezDTO;

import java.util.List;

/**
 * Interfaz IPrimeraVezService.
 * Define los métodos para gestionar las entidades de "Primera Vez", incluyendo operaciones CRUD y consultas personalizadas.
 *
 * @author Matias Borra
 */
public interface IPrimeraVezService {

    /**
     * Obtiene todas las entidades de "Primera Vez".
     *
     * @return Lista de entidades en formato DTO
     */
    List<PrimeraVezDTO> findAll();

    /**
     * Busca una entidad de "Primera Vez" por su ID.
     *
     * @param id ID de la entidad
     * @return Entidad en formato DTO
     */
    PrimeraVezDTO findById(Long id);

    /**
     * Busca una entidad de "Primera Vez" por el ID del chiste asociado.
     *
     * @param jokeId ID del chiste
     * @return Entidad en formato DTO
     */
    PrimeraVezDTO findByJokeId(Long jokeId);

    /**
     * Guarda o actualiza una entidad de "Primera Vez".
     *
     * @param dto DTO de la entidad a guardar o actualizar
     * @return Entidad guardada o actualizada en formato DTO
     */
    PrimeraVezDTO save(PrimeraVezDTO dto);

    /**
     * Elimina una entidad de "Primera Vez" por su ID.
     *
     * @param id ID de la entidad a eliminar
     */
    void delete(Long id);

    /**
     * Elimina una entidad de "Primera Vez" por el ID del chiste asociado.
     *
     * @param jokeId ID del chiste asociado
     */
    void deleteByJokeId(Long jokeId);
}