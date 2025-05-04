package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.FlagDTO;
import com.matiasborra.jokes.dto.FlagJokeDTO;
import com.matiasborra.jokes.model.dao.IFlagDAO;
import com.matiasborra.jokes.model.dao.IJokeDAO;
import com.matiasborra.jokes.model.entity.Flag;
import com.matiasborra.jokes.model.projections.FlagJokeProjection;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de banderas.
 * Proporciona métodos para gestionar las banderas, incluyendo operaciones CRUD y consultas personalizadas.
 * Utiliza ModelMapper para la conversión entre entidades y DTOs.
 *
 * @author Matias Borra
 */
@Service
@Transactional
public class FlagServiceImpl implements IFlagService {

    private final IFlagDAO flagRepo;
    private final ModelMapper mapper;
    private final IJokeDAO jokeRepo;

    /**
     * Constructor de la clase FlagServiceImpl.
     *
     * @param flagRepo Repositorio de banderas
     * @param jokeRepo Repositorio de chistes
     * @param mapper   ModelMapper para la conversión de entidades y DTOs
     */
    public FlagServiceImpl(IFlagDAO flagRepo, IJokeDAO jokeRepo, ModelMapper mapper) {
        this.flagRepo = flagRepo;
        this.jokeRepo = jokeRepo;
        this.mapper = mapper;
    }

    /**
     * Obtiene todas las banderas.
     *
     * @return Lista de banderas en formato DTO
     */
    @Override
    public List<FlagDTO> findAll() {
        return flagRepo.findAll()
                .stream()
                .map(f -> mapper.map(f, FlagDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Busca una bandera por su ID.
     *
     * @param id ID de la bandera
     * @return Bandera en formato DTO
     * @throws RuntimeException si no se encuentra la bandera
     */
    @Override
    public FlagDTO findById(Long id) {
        Flag f = flagRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Flag not found"));
        return mapper.map(f, FlagDTO.class);
    }

    /**
     * Crea una nueva bandera.
     *
     * @param dto DTO de la bandera a crear
     * @return Bandera creada en formato DTO
     */
    @Override
    public FlagDTO create(FlagDTO dto) {
        Flag f = new Flag();
        f.setFlag(dto.getFlag());
        Flag saved = flagRepo.save(f);
        return mapper.map(saved, FlagDTO.class);
    }

    /**
     * Actualiza una bandera existente.
     *
     * @param id  ID de la bandera a actualizar
     * @param dto DTO con los datos actualizados
     * @return Bandera actualizada en formato DTO
     * @throws RuntimeException si no se encuentra la bandera
     */
    @Override
    public FlagDTO update(Long id, FlagDTO dto) {
        Flag f = flagRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Flag not found"));
        f.setFlag(dto.getFlag());
        Flag saved = flagRepo.save(f);
        return mapper.map(saved, FlagDTO.class);
    }

    /**
     * Elimina una bandera por su ID.
     * También desvincula las relaciones con los chistes asociados antes de eliminarla.
     *
     * @param flagId ID de la bandera a eliminar
     * @throws RuntimeException si no se encuentra la bandera
     */
    @Override
    @Transactional
    public void delete(Long flagId) {
        Flag flag = flagRepo.findById(flagId)
                .orElseThrow(() -> new RuntimeException("Flag not found"));

        flag.getJokeFlags().forEach(jf -> jf.getJoke().getJokeFlags().remove(jf));
        flag.getJokeFlags().clear();

        flagRepo.delete(flag);
    }

    /**
     * Lista los chistes asociados a una bandera específica.
     *
     * @param flagId ID de la bandera
     * @return Lista de proyecciones de chistes asociados a la bandera
     */
    @Override
    public List<FlagJokeProjection> listarPorFlag(Long flagId) {
        return jokeRepo.findByFlags_Id(flagId);
    }
}