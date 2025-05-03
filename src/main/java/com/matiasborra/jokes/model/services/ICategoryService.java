package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.CategoryDTO;
import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> findAll();
    CategoryDTO findById(Long id);
    CategoryDTO create(CategoryDTO dto);
    CategoryDTO update(Long id, CategoryDTO dto);
    void delete(Long id);
}