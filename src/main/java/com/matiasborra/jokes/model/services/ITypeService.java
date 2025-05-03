package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.TypeDTO;
import java.util.List;

public interface ITypeService {
    List<TypeDTO> findAll();
    TypeDTO findById(Long id);
    TypeDTO create(TypeDTO dto);
    TypeDTO update(Long id, TypeDTO dto);
    void delete(Long id);
}