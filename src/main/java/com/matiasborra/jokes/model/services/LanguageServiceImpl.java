package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.LanguageDTO;
import com.matiasborra.jokes.model.entity.Joke;
import com.matiasborra.jokes.model.entity.Language;
import com.matiasborra.jokes.model.dao.ILanguageDAO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio ILanguageService.
 * Proporciona métodos para gestionar los idiomas, incluyendo operaciones CRUD.
 * También maneja relaciones con otras entidades como los chistes.
 *
 * @author Matias Borra
 */
@Service
@Transactional
public class LanguageServiceImpl implements ILanguageService {

    private final ILanguageDAO repo;
    private final ModelMapper mapper;

    /**
     * Constructor de LanguageServiceImpl.
     *
     * @param repo   Repositorio de idiomas
     * @param mapper Mapper para convertir entre entidades y DTOs
     */
    public LanguageServiceImpl(ILanguageDAO repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    /**
     * Obtiene todos los idiomas.
     *
     * @return Lista de idiomas en formato DTO
     */
    @Override
    public List<LanguageDTO> findAll() {
        return repo.findAll().stream()
                .map(e -> mapper.map(e, LanguageDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Busca un idioma por su ID.
     *
     * @param id ID del idioma
     * @return Idioma en formato DTO
     */
    @Override
    public LanguageDTO findById(Long id) {
        Language e = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Language not found"));
        return mapper.map(e, LanguageDTO.class);
    }

    /**
     * Crea un nuevo idioma.
     *
     * @param dto DTO del idioma a crear
     * @return Idioma creado en formato DTO
     */
    @Override
    public LanguageDTO create(LanguageDTO dto) {
        Language e = mapper.map(dto, Language.class);
        Language saved = repo.save(e);
        return mapper.map(saved, LanguageDTO.class);
    }

    /**
     * Actualiza un idioma existente.
     *
     * @param id  ID del idioma a actualizar
     * @param dto DTO con los datos actualizados
     * @return Idioma actualizado en formato DTO
     */
    @Override
    public LanguageDTO update(Long id, LanguageDTO dto) {
        Language existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Language not found"));
        existing.setCode(dto.getCode());
        existing.setLanguage(dto.getLanguage());
        Language saved = repo.save(existing);
        return mapper.map(saved, LanguageDTO.class);
    }

    /**
     * Elimina un idioma por su ID.
     * Rompe la relación entre los chistes y el idioma antes de eliminarlo.
     *
     * @param languageId ID del idioma a eliminar
     */
    @Override
    @Transactional
    public void delete(Long languageId) {
        Language language = repo.findById(languageId)
                .orElseThrow(() -> new RuntimeException("Language not found"));

        // Romper la relación entre los chistes y el idioma
        for (Joke joke : language.getJokes()) {
            joke.setLanguage(null);
        }

        // Eliminar el idioma
        repo.delete(language);
    }
}