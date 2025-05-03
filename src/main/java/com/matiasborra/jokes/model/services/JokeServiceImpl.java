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

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class JokeServiceImpl implements IJokeService {

    private final IJokeDAO jokeRepo;
    private final ICategoryDAO categoryRepo;
    private final ITypeDAO typeRepo;
    private final ILanguageDAO languageRepo;
    private final IFlagDAO flagRepo;
    private final ModelMapper mapper;

    public JokeServiceImpl(IJokeDAO jokeRepo,
                           ICategoryDAO categoryRepo,
                           ITypeDAO typeRepo,
                           ILanguageDAO languageRepo,
                           IFlagDAO flagRepo,
                           ModelMapper mapper) {
        this.jokeRepo     = jokeRepo;
        this.categoryRepo = categoryRepo;
        this.typeRepo     = typeRepo;
        this.languageRepo = languageRepo;
        this.flagRepo     = flagRepo;
        this.mapper       = mapper;
    }

    @Override
    public List<JokeDTO> findAll() {
        return jokeRepo.findAllByOrderByIdAsc()
                .stream()
                .map(this::toDto)
                .collect(toList());
    }

    @Override
    public JokeDTO findById(Long id) {
        Joke joke = jokeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Joke not found"));
        return toDto(joke);
    }

    @Override
    public JokeDTO create(JokeDTO dto) {
        // 1) cargar referencias
        Category category = categoryRepo.findById(dto.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Type type = typeRepo.findById(dto.getType().getId())
                .orElseThrow(() -> new RuntimeException("Type not found"));
        Language language = languageRepo.findById(dto.getLanguage().getId())
                .orElseThrow(() -> new RuntimeException("Language not found"));

        // 2) construir entidad
        Joke joke = new Joke();
        joke.setText1(dto.getText1());
        joke.setText2(dto.getText2());
        joke.setCategory(category);
        joke.setType(type);
        joke.setLanguage(language);

        // 3) asociar flags por IDs
        if (dto.getFlagIds() != null) {
            for (Long fid : dto.getFlagIds()) {
                Flag flag = flagRepo.findById(fid)
                        .orElseThrow(() -> new RuntimeException("Flag not found"));
                JokeFlag jf = new JokeFlag();
                jf.setJoke(joke);
                jf.setFlag(flag);
                joke.getJokeFlags().add(jf);
            }
        }

        // 4) guardar y devolver DTO
        Joke saved = jokeRepo.save(joke);
        return toDto(saved);
    }

    @Override
    public JokeDTO update(Long id, JokeDTO dto) {
        Joke existing = jokeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Joke not found"));

        // actualizar texto
        existing.setText1(dto.getText1());
        existing.setText2(dto.getText2());

        // si cambió categoría / tipo / idioma, recargarlas
        if (!existing.getCategory().getId().equals(dto.getCategory().getId())) {
            Category category = categoryRepo.findById(dto.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existing.setCategory(category);
        }
        if (!existing.getType().getId().equals(dto.getType().getId())) {
            Type type = typeRepo.findById(dto.getType().getId())
                    .orElseThrow(() -> new RuntimeException("Type not found"));
            existing.setType(type);
        }
        if (!existing.getLanguage().getId().equals(dto.getLanguage().getId())) {
            Language language = languageRepo.findById(dto.getLanguage().getId())
                    .orElseThrow(() -> new RuntimeException("Language not found"));
            existing.setLanguage(language);
        }

        // reemplazar por completo las flags
        existing.getJokeFlags().clear();
        if (dto.getFlagIds() != null) {
            for (Long fid : dto.getFlagIds()) {
                Flag flag = flagRepo.findById(fid)
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

    /**
     * Helper: convierte entidad a DTO y rellena tanto flags como flagIds
     */
    private JokeDTO toDto(Joke j) {
        JokeDTO dto = mapper.map(j, JokeDTO.class);

        // flags completas para API
        List<FlagDTO> flags = j.getJokeFlags().stream()
                .map(jf -> mapper.map(jf.getFlag(), FlagDTO.class))
                .collect(toList());
        dto.setFlags(flags);

        // ids para Thymeleaf
        List<Long> ids = j.getJokeFlags().stream()
                .map(jf -> jf.getFlag().getId())
                .collect(toList());
        dto.setFlagIds(ids);

        return dto;
    }

    // inyecta IJokeDAO…
    @Override
    public List<JokeDTO> findAllWithPV() {
        return jokeRepo.findAllWithPrimeraVezAndTelefonos()
                .stream()
                .map(this::toJokeWithPVDto)
                .collect(toList());
    }

    private JokeDTO toJokeWithPVDto(Joke j) {
        JokeDTO dto = mapper.map(j, JokeDTO.class);
        if (j.getPrimeraVez() != null) {
            PrimeraVez pv = j.getPrimeraVez();
            dto.setPrograma(pv.getPrograma());
            dto.setFechaEmision(pv.getFechaEmision());
            dto.setTelefonos(
                    pv.getTelefonos().stream()
                            .map(Telefono::getNumero)
                            .collect(toList())
            );
        }
        return dto;
    }

    @Override
    public List<JokeDTO> filterByText(String text) {
        return jokeRepo.findByText1ContainingIgnoreCase(text)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
