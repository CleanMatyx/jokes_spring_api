package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.TypeDTO;
import java.util.List;

/**
 * Interfaz ITypeService.
 * Define los métodos para gestionar los tipos, incluyendo operaciones CRUD.
 *
 * @author Matias Borra
 */
public interface ITypeService {

    /**
     * Obtiene todos los tipos.
     *
     * @return Lista de tipos en formato DTO
     */
    List<TypeDTO> findAll();

    /**
     * Busca un tipo por su ID.
     *
     * @param id ID del tipo
     * @return Tipo en formato DTO
     */
    TypeDTO findById(Long id);

    /**
     * Crea un nuevo tipo.
     *
     * @param dto DTO del tipo a crear
     * @return Tipo creado en formato DTO
     */
    TypeDTO create(TypeDTO dto);

    /**
     * Actualiza un tipo existente.
     *
     * @param id  ID del tipo a actualizar
     * @param dto DTO con los datos actualizados
     * @return Tipo actualizado en formato DTO
     */
    TypeDTO update(Long id, TypeDTO dto);

    /**
     * Elimina un tipo por su ID.
     *
     * @param id ID del tipo a eliminar
     */
    void delete(Long id);
}