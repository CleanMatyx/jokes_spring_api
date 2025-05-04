package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.LanguageDTO;
import java.util.List;

/**
 * Interfaz ILanguageService.
 * Define los métodos para gestionar los idiomas, incluyendo operaciones CRUD.
 *
 * @author Matias
 */
public interface ILanguageService {

    /**
     * Obtiene todos los idiomas.
     *
     * @return Lista de idiomas en formato DTO
     */
    List<LanguageDTO> findAll();

    /**
     * Busca un idioma por su ID.
     *
     * @param id ID del idioma
     * @return Idioma en formato DTO
     */
    LanguageDTO findById(Long id);

    /**
     * Crea un nuevo idioma.
     *
     * @param dto DTO del idioma a crear
     * @return Idioma creado en formato DTO
     */
    LanguageDTO create(LanguageDTO dto);

    /**
     * Actualiza un idioma existente.
     *
     * @param id  ID del idioma a actualizar
     * @param dto DTO con los datos actualizados
     * @return Idioma actualizado en formato DTO
     */
    LanguageDTO update(Long id, LanguageDTO dto);

    /**
     * Elimina un idioma por su ID.
     *
     * @param id ID del idioma a eliminar
     */
    void delete(Long id);
}