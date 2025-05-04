package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.TypeDTO;
import com.matiasborra.jokes.model.entity.Joke;
import com.matiasborra.jokes.model.entity.Type;
import com.matiasborra.jokes.model.dao.ITypeDAO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio ITypeService.
 * Proporciona métodos para gestionar los tipos, incluyendo operaciones CRUD.
 * También maneja relaciones con otras entidades como los chistes.
 *
 * @author Matias
 */
@Service
@Transactional
public class TypeServiceImpl implements ITypeService {

    private final ITypeDAO repo;
    private final ModelMapper mapper;

    /**
     * Constructor de TypeServiceImpl.
     *
     * @param repo   Repositorio de tipos
     * @param mapper Mapper para convertir entre entidades y DTOs
     */
    public TypeServiceImpl(ITypeDAO repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    /**
     * Obtiene todos los tipos.
     *
     * @return Lista de tipos en formato DTO
     */
    @Override
    public List<TypeDTO> findAll() {
        return repo.findAll().stream()
                .map(e -> mapper.map(e, TypeDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Busca un tipo por su ID.
     *
     * @param id ID del tipo
     * @return Tipo en formato DTO
     */
    @Override
    public TypeDTO findById(Long id) {
        Type e = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Type not found"));
        return mapper.map(e, TypeDTO.class);
    }

    /**
     * Crea un nuevo tipo.
     *
     * @param dto DTO del tipo a crear
     * @return Tipo creado en formato DTO
     */
    @Override
    public TypeDTO create(TypeDTO dto) {
        Type e = mapper.map(dto, Type.class);
        Type saved = repo.save(e);
        return mapper.map(saved, TypeDTO.class);
    }

    /**
     * Actualiza un tipo existente.
     *
     * @param id  ID del tipo a actualizar
     * @param dto DTO con los datos actualizados
     * @return Tipo actualizado en formato DTO
     */
    @Override
    public TypeDTO update(Long id, TypeDTO dto) {
        Type existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Type not found"));
        existing.setType(dto.getType());
        Type saved = repo.save(existing);
        return mapper.map(saved, TypeDTO.class);
    }

    /**
     * Elimina un tipo por su ID.
     * Rompe la relación entre los chistes y el tipo antes de eliminarlo.
     *
     * @param typeId ID del tipo a eliminar
     */
    @Override
    @Transactional
    public void delete(Long typeId) {
        Type type = repo.findById(typeId)
                .orElseThrow(() -> new RuntimeException("Type not found"));

        // Romper la relación entre los chistes y el tipo
        for (Joke joke : type.getJokes()) {
            joke.setType(null);
        }

        // Eliminar el tipo
        repo.delete(type);
    }
}