package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.LanguageDTO;
import java.util.List;

public interface ILanguageService {
    List<LanguageDTO> findAll();
    LanguageDTO findById(Long id);
    LanguageDTO create(LanguageDTO dto);
    LanguageDTO update(Long id, LanguageDTO dto);
    void delete(Long id);
}