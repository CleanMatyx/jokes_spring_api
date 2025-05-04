package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.JokeDTO;

import java.util.List;

/**
 * Interfaz IJokeService.
 * Define los métodos para gestionar los chistes, incluyendo operaciones CRUD y consultas personalizadas.
 *
 * @author Matias Borra
 */
public interface IJokeService {

    /**
     * Obtiene todos los chistes.
     *
     * @return Lista de chistes en formato DTO
     */
    List<JokeDTO> findAll();

    /**
     * Busca un chiste por su ID.
     *
     * @param id ID del chiste
     * @return Chiste en formato DTO
     */
    JokeDTO findById(Long id);

    /**
     * Crea un nuevo chiste.
     *
     * @param dto DTO del chiste a crear
     * @return Chiste creado en formato DTO
     */
    JokeDTO create(JokeDTO dto);

    /**
     * Actualiza un chiste existente.
     *
     * @param id  ID del chiste a actualizar
     * @param dto DTO con los datos actualizados
     * @return Chiste actualizado en formato DTO
     */
    JokeDTO update(Long id, JokeDTO dto);

    /**
     * Elimina un chiste por su ID.
     *
     * @param id ID del chiste a eliminar
     */
    void delete(Long id);

    /**
     * Obtiene todos los chistes con PV.
     *
     * @return Lista de chistes con PV en formato DTO
     */
    List<JokeDTO> findAllWithPV();

    /**
     * Obtiene todos los chistes sin PV.
     *
     * @return Lista de chistes sin PV en formato DTO
     */
    List<JokeDTO> findAllWithoutPV();

    /**
     * Filtra los chistes por texto.
     *
     * @param q Texto a buscar
     * @return Lista de chistes que coinciden con el filtro en formato DTO
     */
    List<JokeDTO> filterByText(String q);
}