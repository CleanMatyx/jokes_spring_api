package com.matiasborra.jokes.model.dao;

import com.matiasborra.jokes.model.entity.PrimeraVez;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * DAO para la entidad PrimeraVez.
 * Proporciona métodos para realizar operaciones CRUD en la base de datos,
 * incluyendo búsquedas y eliminaciones específicas por ID de Joke.
 *
 * @author Matias Borra
 */
public interface IPrimeraVezDAO extends JpaRepository<PrimeraVez, Long> {

    /**
     * Busca una entidad PrimeraVez por el ID de su Joke asociado.
     *
     * @param jokeId ID del Joke asociado
     * @return Entidad PrimeraVez envuelta en un Optional
     */
    Optional<PrimeraVez> findByJoke_Id(Long jokeId);

    /**
     * Elimina una entidad PrimeraVez por el ID de su Joke asociado.
     *
     * @param jokeId ID del Joke asociado
     */
    void deleteByJoke_Id(Long jokeId);
}