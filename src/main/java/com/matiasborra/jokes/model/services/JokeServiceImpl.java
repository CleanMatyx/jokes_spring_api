package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.FlagDTO;
import com.matiasborra.jokes.dto.JokeDTO;
import com.matiasborra.jokes.model.dao.*;
import com.matiasborra.jokes.model.entity.Joke;
import com.matiasborra.jokes.model.entity.JokeFlag;
import com.matiasborra.jokes.model.entity.PrimeraVez;
import com.matiasborra.jokes.model.entity.Telefono;
import com.matiasborra.jokes.model.entity.Category;
import com.matiasborra.jokes.model.entity.Type;
import com.matiasborra.jokes.model.entity.Language;
import com.matiasborra.jokes.model.entity.Flag;
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

    /** Listar todos los chistes (para API y Web) **/
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
        Joke j = buildEntityFromDto(dto, new Joke());
        Joke saved = jokeRepo.save(j);
        return toDto(saved);
    }

    @Override
    public JokeDTO update(Long id, JokeDTO dto) {
        Joke existing = jokeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Joke not found"));
        Joke j = buildEntityFromDto(dto, existing);
        Joke saved = jokeRepo.save(j);
        return toDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        Joke joke = jokeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Chiste no encontrado"));

        // 1) Limpiar las flags (join‐entities)
        joke.getJokeFlags().clear();

        // 2) Limpiar la relación OneToOne con PrimeraVez (si existe)
        if (joke.getPrimeraVez() != null) {
            // rompe la bidireccionalidad
            PrimeraVez pv = joke.getPrimeraVez();
            pv.setJoke(null);                // rompe el FK
            joke.setPrimeraVez(null);        // orphanRemoval lo borrará
        }

        // 3) Forzar el flush para que Hibernate ejecute los DELETE en las tablas hijas
        jokeRepo.flush();

        // 4) Ahora sí, borrar el chiste
        jokeRepo.deleteById(id);
    }

    /** Construye o actualiza la entidad Joke a partir de un DTO **/
    private Joke buildEntityFromDto(JokeDTO dto, Joke joke) {
        // actualizar texto
        joke.setText1(dto.getText1());
        joke.setText2(dto.getText2());

        // categoría, tipo, idioma
        Category cat = categoryRepo.findById(dto.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Type type = typeRepo.findById(dto.getType().getId())
                .orElseThrow(() -> new RuntimeException("Type not found"));
        Language lang = languageRepo.findById(dto.getLanguage().getId())
                .orElseThrow(() -> new RuntimeException("Language not found"));
        joke.setCategory(cat);
        joke.setType(type);
        joke.setLanguage(lang);

        // flags
        joke.getJokeFlags().clear();
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

        return joke;
    }

    /** Mapea básica a DTO (texto, relaciones y flags) **/
    private JokeDTO toDto(Joke j) {
        JokeDTO dto = mapper.map(j, JokeDTO.class);

        // flags para API
        List<FlagDTO> flags = j.getJokeFlags().stream()
                .map(jf -> mapper.map(jf.getFlag(), FlagDTO.class))
                .collect(toList());
        dto.setFlags(flags);

        // flagIds para Web
        List<Long> ids = flags.stream()
                .map(FlagDTO::getId)
                .collect(toList());
        dto.setFlagIds(ids);

        return dto;
    }

    /** --------------------------------------------------------
     *  Métodos extra para “Jokes con PrimeraVez y Teléfonos”
     *  -------------------------------------------------------*/

    @Override
    public List<JokeDTO> findAllWithPV() {
        return jokeRepo.findAllWithPrimeraVezAndTelefonos()
                .stream()
                .map(this::toDtoWithPV)
                .collect(toList());
    }

    @Override
    public List<JokeDTO> findAllWithoutPV() {
        return jokeRepo.findByPrimeraVezIsNullOrderByIdAsc()
                .stream().map(this::toDto).collect(toList());
    }

    /** Concatena al DTO los datos de primera vez y teléfonos **/
    private JokeDTO toDtoWithPV(Joke j) {
        // primero el mapeo común
        JokeDTO dto = toDto(j);

        // si existe primeraVez, lo llenamos
        PrimeraVez pv = j.getPrimeraVez();
        if (pv != null) {
            dto.setPrograma(pv.getPrograma());
            dto.setFechaEmision(pv.getFechaEmision());
            List<String> tels = pv.getTelefonos().stream()
                    .map(Telefono::getNumero)
                    .collect(toList());
            dto.setTelefonos(tels);
        }
        return dto;
    }

    /**
     *  Filtrar por texto1 (no case sensitive)
     */
    @Override
    public List<JokeDTO> filterByText(String text) {
        return jokeRepo.findByText1ContainingIgnoreCase(text)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
