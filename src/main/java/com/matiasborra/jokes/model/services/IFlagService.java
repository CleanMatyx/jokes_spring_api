package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.FlagDTO;
import com.matiasborra.jokes.model.projections.FlagJokeProjection;

import java.util.List;

public interface IFlagService {
    List<FlagDTO> findAll();
    FlagDTO findById(Long id);
    FlagDTO create(FlagDTO dto);
    FlagDTO update(Long id, FlagDTO dto);
    void delete(Long id);
    List<FlagJokeProjection> listarPorFlag(Long flagId);

}
