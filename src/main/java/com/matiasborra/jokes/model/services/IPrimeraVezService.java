package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.PrimeraVezDTO;

import java.util.List;

public interface IPrimeraVezService {

    /** Para GET /api/primera_vez */
    List<PrimeraVezDTO> findAll();

    /** Para GET /api/primera_vez/{id} */
    PrimeraVezDTO findById(Long id);

    /** Para GET /api/primera_vez/joke/{jokeId} */
    PrimeraVezDTO findByJokeId(Long jokeId);

    /** Para POST y PUT */
    PrimeraVezDTO save(PrimeraVezDTO dto);

    /** Para DELETE /api/primera_vez/{id} */
    void delete(Long id);

    /** Para DELETE /api/primera_vez/joke/{jokeId} */
    void deleteByJokeId(Long jokeId);
}
