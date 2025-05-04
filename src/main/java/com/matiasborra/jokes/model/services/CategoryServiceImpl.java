package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.CategoryDTO;
import com.matiasborra.jokes.model.entity.Category;
import com.matiasborra.jokes.model.dao.ICategoryDAO;
import com.matiasborra.jokes.model.entity.Joke;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de categorías.
 * Proporciona métodos para gestionar las categorías, incluyendo operaciones CRUD.
 *
 * @author Matias Borra
 */
@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryDAO repo;
    private final ModelMapper mapper;

    /**
     * Constructor de la clase CategoryServiceImpl.
     *
     * @param repo   Repositorio de categorías
     * @param mapper ModelMapper para la conversión de entidades y DTOs
     */
    public CategoryServiceImpl(ICategoryDAO repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    /**
     * Obtiene todas las categorías.
     *
     * @return Lista de categorías en formato DTO
     */
    @Override
    public List<CategoryDTO> findAll() {
        return repo.findAll().stream()
                .map(e -> mapper.map(e, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Busca una categoría por su ID.
     *
     * @param id ID de la categoría
     * @return Categoría en formato DTO
     * @throws RuntimeException si no se encuentra la categoría
     */
    @Override
    public CategoryDTO findById(Long id) {
        Category e = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return mapper.map(e, CategoryDTO.class);
    }

    /**
     * Crea una nueva categoría.
     *
     * @param dto DTO de la categoría a crear
     * @return Categoría creada en formato DTO
     */
    @Override
    public CategoryDTO create(CategoryDTO dto) {
        Category e = mapper.map(dto, Category.class);
        Category saved = repo.save(e);
        return mapper.map(saved, CategoryDTO.class);
    }

    /**
     * Actualiza una categoría existente.
     *
     * @param id  ID de la categoría a actualizar
     * @param dto DTO con los datos actualizados
     * @return Categoría actualizada en formato DTO
     * @throws RuntimeException si no se encuentra la categoría
     */
    @Override
    public CategoryDTO update(Long id, CategoryDTO dto) {
        Category existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        existing.setCategory(dto.getCategory());
        Category saved = repo.save(existing);
        return mapper.map(saved, CategoryDTO.class);
    }

    /**
     * Elimina una categoría por su ID.
     * También desvincula los chistes asociados a la categoría antes de eliminarla.
     *
     * @param categoryId ID de la categoría a eliminar
     * @throws EntityNotFoundException si no se encuentra la categoría
     */
    @Transactional
    public void delete(Long categoryId) {
        Category category = repo.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        for (Joke joke : category.getJokes()) {
            joke.setCategory(null);
        }

        repo.delete(category);
    }
}