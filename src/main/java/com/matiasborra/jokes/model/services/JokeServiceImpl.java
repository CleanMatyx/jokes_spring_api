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

/**
 * Implementación del servicio IJokeService.
 * Proporciona métodos para gestionar los chistes, incluyendo operaciones CRUD y consultas personalizadas.
 * También maneja relaciones con otras entidades como categorías, tipos, idiomas y banderas.
 *
 * @author Matias Borra
 */
@Service
@Transactional
public class JokeServiceImpl implements IJokeService {

    private final IJokeDAO jokeRepo;
    private final ICategoryDAO categoryRepo;
    private final ITypeDAO typeRepo;
    private final ILanguageDAO languageRepo;
    private final IFlagDAO flagRepo;
    private final ModelMapper mapper;

    /**
     * Constructor de JokeServiceImpl.
     *
     * @param jokeRepo     Repositorio de chistes
     * @param categoryRepo Repositorio de categorías
     * @param typeRepo     Repositorio de tipos
     * @param languageRepo Repositorio de idiomas
     * @param flagRepo     Repositorio de banderas
     * @param mapper       Mapper para convertir entre entidades y DTOs
     */
    public JokeServiceImpl(IJokeDAO jokeRepo,
                           ICategoryDAO categoryRepo,
                           ITypeDAO typeRepo,
                           ILanguageDAO languageRepo,
                           IFlagDAO flagRepo,
                           ModelMapper mapper) {
        this.jokeRepo = jokeRepo;
        this.categoryRepo = categoryRepo;
        this.typeRepo = typeRepo;
        this.languageRepo = languageRepo;
        this.flagRepo = flagRepo;
        this.mapper = mapper;
    }

    /**
     * Obtiene todos los chistes ordenados por ID de forma ascendente.
     *
     * @return Lista de chistes en formato DTO
     */
    @Override
    public List<JokeDTO> findAll() {
        return jokeRepo.findAllByOrderByIdAsc()
                .stream()
                .map(this::toDto)
                .collect(toList());
    }

    /**
     * Busca un chiste por su ID.
     *
     * @param id ID del chiste
     * @return Chiste en formato DTO
     */
    @Override
    public JokeDTO findById(Long id) {
        Joke joke = jokeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Joke not found"));
        return toDto(joke);
    }

    /**
     * Crea un nuevo chiste.
     *
     * @param dto DTO del chiste a crear
     * @return Chiste creado en formato DTO
     */
    @Override
    public JokeDTO create(JokeDTO dto) {
        Joke j = buildEntityFromDto(dto, new Joke());
        Joke saved = jokeRepo.save(j);
        return toDto(saved);
    }

    /**
     * Actualiza un chiste existente.
     *
     * @param id  ID del chiste a actualizar
     * @param dto DTO con los datos actualizados
     * @return Chiste actualizado en formato DTO
     */
    @Override
    public JokeDTO update(Long id, JokeDTO dto) {
        Joke existing = jokeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Joke not found"));
        Joke j = buildEntityFromDto(dto, existing);
        Joke saved = jokeRepo.save(j);
        return toDto(saved);
    }

    /**
     * Elimina un chiste por su ID.
     *
     * @param id ID del chiste a eliminar
     */
    @Transactional
    public void delete(Long id) {
        Joke joke = jokeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Chiste no encontrado"));

        joke.getJokeFlags().clear();

        if (joke.getPrimeraVez() != null) {
            PrimeraVez pv = joke.getPrimeraVez();
            pv.setJoke(null);
            joke.setPrimeraVez(null);
        }

        jokeRepo.flush();
        jokeRepo.deleteById(id);
    }

    /**
     * Construye o actualiza la entidad Joke a partir de un DTO.
     *
     * @param dto  DTO con los datos del chiste
     * @param joke Entidad Joke a actualizar o crear
     * @return Entidad Joke actualizada o creada
     */
    private Joke buildEntityFromDto(JokeDTO dto, Joke joke) {
        joke.setText1(dto.getText1());
        joke.setText2(dto.getText2());

        Category cat = categoryRepo.findById(dto.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Type type = typeRepo.findById(dto.getType().getId())
                .orElseThrow(() -> new RuntimeException("Type not found"));
        Language lang = languageRepo.findById(dto.getLanguage().getId())
                .orElseThrow(() -> new RuntimeException("Language not found"));
        joke.setCategory(cat);
        joke.setType(type);
        joke.setLanguage(lang);

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

    /**
     * Convierte una entidad Joke a un DTO.
     *
     * @param j Entidad Joke
     * @return DTO del chiste
     */
    private JokeDTO toDto(Joke j) {
        JokeDTO dto = mapper.map(j, JokeDTO.class);

        List<FlagDTO> flags = j.getJokeFlags().stream()
                .map(jf -> mapper.map(jf.getFlag(), FlagDTO.class))
                .collect(toList());
        dto.setFlags(flags);

        List<Long> ids = flags.stream()
                .map(FlagDTO::getId)
                .collect(toList());
        dto.setFlagIds(ids);

        return dto;
    }

    /**
     * Obtiene todos los chistes con datos de "Primera Vez" y teléfonos.
     *
     * @return Lista de chistes en formato DTO
     */
    @Override
    public List<JokeDTO> findAllWithPV() {
        return jokeRepo.findAllWithPrimeraVezAndTelefonos()
                .stream()
                .map(this::toDtoWithPV)
                .collect(toList());
    }

    /**
     * Obtiene todos los chistes sin datos de "Primera Vez".
     *
     * @return Lista de chistes en formato DTO
     */
    @Override
    public List<JokeDTO> findAllWithoutPV() {
        return jokeRepo.findByPrimeraVezIsNullOrderByIdAsc()
                .stream()
                .map(this::toDto)
                .collect(toList());
    }

    /**
     * Convierte una entidad Joke a un DTO, incluyendo datos de "Primera Vez" y teléfonos.
     *
     * @param j Entidad Joke
     * @return DTO del chiste
     */
    private JokeDTO toDtoWithPV(Joke j) {
        JokeDTO dto = toDto(j);

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
     * Filtra los chistes por texto1 (sin distinción de mayúsculas y minúsculas).
     *
     * @param text Texto a buscar
     * @return Lista de chistes que coinciden con el filtro en formato DTO
     */
    @Override
    public List<JokeDTO> filterByText(String text) {
        return jokeRepo.findByText1ContainingIgnoreCase(text)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}