package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.CategoryDTO;
import java.util.List;

/**
 * Interfaz ICategoryService.
 * Define los métodos para gestionar las categorías, incluyendo operaciones CRUD.
 *
 * @author Matias Borra
 */
public interface ICategoryService {

    /**
     * Obtiene todas las categorías.
     *
     * @return Lista de categorías en formato DTO
     */
    List<CategoryDTO> findAll();

    /**
     * Busca una categoría por su ID.
     *
     * @param id ID de la categoría
     * @return Categoría en formato DTO
     */
    CategoryDTO findById(Long id);

    /**
     * Crea una nueva categoría.
     *
     * @param dto DTO de la categoría a crear
     * @return Categoría creada en formato DTO
     */
    CategoryDTO create(CategoryDTO dto);

    /**
     * Actualiza una categoría existente.
     *
     * @param id  ID de la categoría a actualizar
     * @param dto DTO con los datos actualizados
     * @return Categoría actualizada en formato DTO
     */
    CategoryDTO update(Long id, CategoryDTO dto);

    /**
     * Elimina una categoría por su ID.
     *
     * @param id ID de la categoría a eliminar
     */
    void delete(Long id);
}