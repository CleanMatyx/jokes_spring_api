package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.FlagDTO;
import com.matiasborra.jokes.dto.JokeDTO;
import com.matiasborra.jokes.model.dao.*;
import com.matiasborra.jokes.model.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class JokeServiceImpl implements IJokeService {

    private final IJokeDAO jokeRepo;
    private final ICategoryDAO categoryRepo;
    private final ITypeDAO typeRepo;
    private final ILanguageDAO languageRepo;
    private final IFlagDAO flagRepo;
    private final ModelMapper mapper;

    public JokeServiceImpl(IJokeDAO jokeRepo, ICategoryDAO categoryRepo, ITypeDAO typeRepo,
                           ILanguageDAO languageRepo, IFlagDAO flagRepo, ModelMapper mapper) {
        this.jokeRepo     = jokeRepo;
        this.categoryRepo = categoryRepo;
        this.typeRepo     = typeRepo;
        this.languageRepo = languageRepo;
        this.flagRepo     = flagRepo;
        this.mapper       = mapper;
    }

    @Override
    public List<JokeDTO> findAll() {
        List<Joke> jokes = jokeRepo.findAllByOrderByIdAsc();
        return jokes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public JokeDTO findById(Long id) {
        Joke joke = jokeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Joke not found"));
        return toDto(joke);
    }

    @Override
    public JokeDTO create(JokeDTO dto) {
        // 1. Cargar las referencias completas
        Category category = categoryRepo.findById(dto.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Type type = typeRepo.findById(dto.getType().getId())
                .orElseThrow(() -> new RuntimeException("Type not found"));
        Language language = languageRepo.findById(dto.getLanguage().getId())
                .orElseThrow(() -> new RuntimeException("Language not found"));

        // 2. Construir la entidad Joke a partir del DTO
        Joke joke = new Joke();
        joke.setText1(dto.getText1());
        joke.setText2(dto.getText2());
        joke.setCategory(category);
        joke.setType(type);
        joke.setLanguage(language);

        // 3. Asociar flags
        if (dto.getFlags() != null) {
            for (FlagDTO fDto : dto.getFlags()) {
                Flag flag = flagRepo.findById(fDto.getId())
                        .orElseThrow(() -> new RuntimeException("Flag not found"));
                JokeFlag jf = new JokeFlag();
                jf.setJoke(joke);
                jf.setFlag(flag);
                joke.getJokeFlags().add(jf);
            }
        }

        // 4. Guardar y retornar DTO completo
        Joke saved = jokeRepo.save(joke);
        return toDto(saved);
    }

    @Override
    public JokeDTO update(Long id, JokeDTO dto) {
        Joke existing = jokeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Joke not found"));

        // Actualizar campos simples
        existing.setText1(dto.getText1());
        existing.setText2(dto.getText2());

        // Si cambian category/type/language, recargarlas
        if (existing.getCategory().getId() != (dto.getCategory().getId())) {
            Category category = categoryRepo.findById(dto.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existing.setCategory(category);
        }
        if (existing.getType().getId() != (dto.getType().getId())) {
            Type type = typeRepo.findById(dto.getType().getId())
                    .orElseThrow(() -> new RuntimeException("Type not found"));
            existing.setType(type);
        }
        if (existing.getLanguage().getId() != (dto.getLanguage().getId())) {
            Language language = languageRepo.findById(dto.getLanguage().getId())
                    .orElseThrow(() -> new RuntimeException("Language not found"));
            existing.setLanguage(language);
        }

        // Reemplazar flags por completo
        existing.getJokeFlags().clear();
        if (dto.getFlags() != null) {
            for (FlagDTO fDto : dto.getFlags()) {
                Flag flag = flagRepo.findById(fDto.getId())
                        .orElseThrow(() -> new RuntimeException("Flag not found"));
                JokeFlag jf = new JokeFlag();
                jf.setJoke(existing);
                jf.setFlag(flag);
                existing.getJokeFlags().add(jf);
            }
        }

        Joke saved = jokeRepo.save(existing);
        return toDto(saved);
    }

    @Override
    public void delete(Long id) {
        jokeRepo.deleteById(id);
    }

    /** Helper para convertir entidad a DTO incluyendo flags **/
    private JokeDTO toDto(Joke j) {
        // Mapea campos básicos
        JokeDTO dto = mapper.map(j, JokeDTO.class);
        // Mapea manualmente la lista de flags
        List<FlagDTO> flags = j.getJokeFlags().stream()
                .map(jf -> mapper.map(jf.getFlag(), FlagDTO.class))
                .collect(Collectors.toList());
        dto.setFlags(flags);
        return dto;
    }
}
