package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.CategoryDTO;
import com.matiasborra.jokes.model.entity.Category;
import com.matiasborra.jokes.model.dao.ICategoryDAO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryDAO repo;
    private final ModelMapper mapper;

    public CategoryServiceImpl(ICategoryDAO repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public List<CategoryDTO> findAll() {
        return repo.findAll().stream()
                .map(e -> mapper.map(e, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Long id) {
        Category e = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return mapper.map(e, CategoryDTO.class);
    }

    @Override
    public CategoryDTO create(CategoryDTO dto) {
        Category e = mapper.map(dto, Category.class);
        Category saved = repo.save(e);
        return mapper.map(saved, CategoryDTO.class);
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO dto) {
        Category existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        // sólo el campo 'category'
        existing.setCategory(dto.getCategory());
        Category saved = repo.save(existing);
        return mapper.map(saved, CategoryDTO.class);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}