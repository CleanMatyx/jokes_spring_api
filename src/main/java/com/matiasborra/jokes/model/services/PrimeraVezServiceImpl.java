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

@Service
@Transactional
public class PrimeraVezServiceImpl implements IPrimeraVezService {
    private final IPrimeraVezDAO pvRepo;
    private final ITelefonoDAO telRepo;
    private final IJokeDAO jokeRepo;
    private final ModelMapper mapper;

    public PrimeraVezServiceImpl(IPrimeraVezDAO pvRepo, ITelefonoDAO telRepo,
                                 IJokeDAO jokeRepo, ModelMapper mapper) {
        this.pvRepo = pvRepo;
        this.telRepo = telRepo;
        this.jokeRepo = jokeRepo;
        this.mapper = mapper;
    }

    @Override
    public PrimeraVezDTO findByJokeId(Long jokeId) {
        return pvRepo.findByJoke_Id(jokeId)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public PrimeraVezDTO findById(Long id) {
        return pvRepo.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("No existe PrimeraVez con id=" + id));
    }

//    @Override
//    public PrimeraVezDTO save(PrimeraVezDTO dto) {
//        PrimeraVez pv = dto.getId() == null
//                ? new PrimeraVez()
//                : pvRepo.findById(dto.getId())
//                .orElseThrow(() -> new RuntimeException("No existe PrimeraVez id=" + dto.getId()));
//        // asociar joke
//        pv.setJoke(jokeRepo.findById(dto.getJokeId())
//                .orElseThrow(() -> new RuntimeException("Joke no encontrado")));
//        pv.setPrograma(dto.getPrograma());
//        pv.setFechaEmision(dto.getFechaEmision());
//
//        // teléfonos: borramos+reasignamos (cascade)
//        pv.getTelefonos().clear();
//        for (TelefonoDTO tDto : dto.getTelefonos()) {
//            Telefono t = new Telefono();
//            t.setNumero(tDto.getNumero());
//            t.setPrimeraVez(pv);
//            pv.getTelefonos().add(t);
//        }
//        PrimeraVez saved = pvRepo.save(pv);
//        return toDto(saved);
//    }

    @Override
    public PrimeraVezDTO save(PrimeraVezDTO dto) {
        PrimeraVez pv;

        // 1) Si trae id, cargo por id
        if (dto.getId() != null) {
            pv = pvRepo.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("No existe PrimeraVez id=" + dto.getId()));
        }
        // 2) Si no trae id pero sí trae jokeId y ya hay una fila con ese joke, lo cargo
        else if (dto.getJokeId() != null) {
            pv = pvRepo.findByJoke_Id(dto.getJokeId())
                    .orElse(new PrimeraVez());
        }
        // 3) Si no hay ni id ni registro previo, creo uno nuevo
        else {
            pv = new PrimeraVez();
        }

        // Asocio el chiste (siempre hay que hacerlo, tanto en insert como en update)
        pv.setJoke(jokeRepo.findById(dto.getJokeId())
                .orElseThrow(() -> new RuntimeException("Joke no encontrado")));
        pv.setPrograma(dto.getPrograma());
        pv.setFechaEmision(dto.getFechaEmision());

        // Manejo de teléfonos (borro los antiguos y reasigno los del DTO)
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


    @Override
    public void deleteByJokeId(Long jokeId) {
        pvRepo.deleteByJoke_Id(jokeId);
    }

    @Override
    public void delete(Long id) {
        if (!pvRepo.existsById(id)) {
            throw new RuntimeException("No existe PrimeraVez con id=" + id);
        }
        pvRepo.deleteById(id);
    }

    @Override
    public List<PrimeraVezDTO> findAll() {
        return pvRepo.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

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