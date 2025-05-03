package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.LanguageDTO;
import com.matiasborra.jokes.model.entity.Language;
import com.matiasborra.jokes.model.dao.ILanguageDAO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LanguageServiceImpl implements ILanguageService {

    private final ILanguageDAO repo;
    private final ModelMapper mapper;

    public LanguageServiceImpl(ILanguageDAO repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public List<LanguageDTO> findAll() {
        return repo.findAll().stream()
                .map(e -> mapper.map(e, LanguageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public LanguageDTO findById(Long id) {
        Language e = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Language not found"));
        return mapper.map(e, LanguageDTO.class);
    }

    @Override
    public LanguageDTO create(LanguageDTO dto) {
        Language e = mapper.map(dto, Language.class);
        Language saved = repo.save(e);
        return mapper.map(saved, LanguageDTO.class);
    }

    @Override
    public LanguageDTO update(Long id, LanguageDTO dto) {
        Language existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Language not found"));
        existing.setCode(dto.getCode());
        existing.setLanguage(dto.getLanguage());
        Language saved = repo.save(existing);
        return mapper.map(saved, LanguageDTO.class);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}