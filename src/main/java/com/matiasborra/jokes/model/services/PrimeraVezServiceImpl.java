package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.PrimeraVezDTO;
import com.matiasborra.jokes.dto.TelefonoDTO;
import com.matiasborra.jokes.model.dao.IPrimeraVezDAO;
import com.matiasborra.jokes.model.dao.IJokeDAO;
import com.matiasborra.jokes.model.dao.ITelefonoDAO;
import com.matiasborra.jokes.model.entity.Joke;
import com.matiasborra.jokes.model.entity.PrimeraVez;
import com.matiasborra.jokes.model.entity.Telefono;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.List;

/**
 * Implementación del servicio IPrimeraVezService.
 * Proporciona métodos para gestionar las entidades de "Primera Vez", incluyendo operaciones CRUD.
 * También maneja relaciones con otras entidades como chistes y teléfonos.
 *
 * @author Matias
 */
@Service
@Transactional
public class PrimeraVezServiceImpl implements IPrimeraVezService {

    private final IPrimeraVezDAO pvRepo;
    private final ITelefonoDAO telRepo;
    private final IJokeDAO jokeRepo;
    private final ModelMapper mapper;

    /**
     * Constructor de PrimeraVezServiceImpl.
     *
     * @param pvRepo   Repositorio de "Primera Vez"
     * @param telRepo  Repositorio de teléfonos
     * @param jokeRepo Repositorio de chistes
     * @param mapper   Mapper para convertir entre entidades y DTOs
     */
    public PrimeraVezServiceImpl(IPrimeraVezDAO pvRepo, ITelefonoDAO telRepo,
                                 IJokeDAO jokeRepo, ModelMapper mapper) {
        this.pvRepo = pvRepo;
        this.telRepo = telRepo;
        this.jokeRepo = jokeRepo;
        this.mapper = mapper;
    }

    /**
     * Busca una entidad de "Primera Vez" por el ID del chiste asociado.
     *
     * @param jokeId ID del chiste
     * @return Entidad de "Primera Vez" en formato DTO
     */
    @Override
    public PrimeraVezDTO findByJokeId(Long jokeId) {
        return pvRepo.findByJoke_Id(jokeId)
                .map(this::toDto)
                .orElse(null);
    }

    /**
     * Busca una entidad de "Primera Vez" por su ID.
     *
     * @param id ID de la entidad
     * @return Entidad de "Primera Vez" en formato DTO
     */
    @Override
    public PrimeraVezDTO findById(Long id) {
        return pvRepo.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("No existe PrimeraVez con id=" + id));
    }

    /**
     * Guarda o actualiza una entidad de "Primera Vez".
     *
     * @param dto DTO de la entidad a guardar o actualizar
     * @return Entidad guardada o actualizada en formato DTO
     */
    @Override
    public PrimeraVezDTO save(PrimeraVezDTO dto) {
        PrimeraVez pv;

        if (dto.getId() != null) {
            pv = pvRepo.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("No existe PrimeraVez id=" + dto.getId()));
        } else if (dto.getJokeId() != null) {
            pv = pvRepo.findByJoke_Id(dto.getJokeId())
                    .orElse(new PrimeraVez());
        } else {
            pv = new PrimeraVez();
        }

        pv.setJoke(jokeRepo.findById(dto.getJokeId())
                .orElseThrow(() -> new RuntimeException("Joke no encontrado")));
        pv.setPrograma(dto.getPrograma());
        pv.setFechaEmision(dto.getFechaEmision());

        pv.getTelefonos().clear();
        for (TelefonoDTO tDto : dto.getTelefonos()) {
            Telefono t = new Telefono();
            t.setNumero(tDto.getNumero());
            t.setPrimeraVez(pv);
            pv.getTelefonos().add(t);
        }

        PrimeraVez saved = pvRepo.save(pv);
        return toDto(saved);
    }

    /**
     * Elimina una entidad de "Primera Vez" por el ID del chiste asociado.
     *
     * @param jokeId ID del chiste asociado
     */
    @Override
    public void deleteByJokeId(Long jokeId) {
        pvRepo.deleteByJoke_Id(jokeId);
    }

    /**
     * Elimina una entidad de "Primera Vez" por su ID.
     *
     * @param id ID de la entidad a eliminar
     */
    @Override
    public void delete(Long id) {
        if (!pvRepo.existsById(id)) {
            throw new RuntimeException("No existe PrimeraVez con id=" + id);
        }
        pvRepo.deleteById(id);
    }

    /**
     * Obtiene todas las entidades de "Primera Vez".
     *
     * @return Lista de entidades en formato DTO
     */
    @Override
    public List<PrimeraVezDTO> findAll() {
        return pvRepo.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Convierte una entidad de "Primera Vez" a un DTO.
     *
     * @param pv Entidad de "Primera Vez"
     * @return DTO de la entidad
     */
    private PrimeraVezDTO toDto(PrimeraVez pv) {
        PrimeraVezDTO dto = mapper.map(pv, PrimeraVezDTO.class);
        dto.setJokeId(pv.getJoke().getId());
        List<TelefonoDTO> tdtos = pv.getTelefonos().stream()
                .map(t -> mapper.map(t, TelefonoDTO.class))
                .collect(Collectors.toList());
        dto.setTelefonos(tdtos);
        return dto;
    }
}