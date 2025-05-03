package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.TypeDTO;
import com.matiasborra.jokes.model.entity.Type;
import com.matiasborra.jokes.model.dao.ITypeDAO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TypeServiceImpl implements ITypeService {

    private final ITypeDAO repo;
    private final ModelMapper mapper;

    public TypeServiceImpl(ITypeDAO repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public List<TypeDTO> findAll() {
        return repo.findAll().stream()
                .map(e -> mapper.map(e, TypeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TypeDTO findById(Long id) {
        Type e = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Type not found"));
        return mapper.map(e, TypeDTO.class);
    }

    @Override
    public TypeDTO create(TypeDTO dto) {
        Type e = mapper.map(dto, Type.class);
        Type saved = repo.save(e);
        return mapper.map(saved, TypeDTO.class);
    }

    @Override
    public TypeDTO update(Long id, TypeDTO dto) {
        Type existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Type not found"));
        existing.setType(dto.getType());
        Type saved = repo.save(existing);
        return mapper.map(saved, TypeDTO.class);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}