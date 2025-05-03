// src/main/java/com/matiasborra/jokes/model/dao/IJokeDAO.java
package com.matiasborra.jokes.model.dao;

import com.matiasborra.jokes.model.entity.Joke;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface IJokeDAO extends JpaRepository<Joke, Long> {

    @Override
    @EntityGraph(attributePaths = {
            "category",
            "type",
            "language",
            "jokeFlags",
            "jokeFlags.flag"
    })
    List<Joke> findAll();

    @Override
    @EntityGraph(attributePaths = {
            "category",
            "type",
            "language",
            "jokeFlags",
            "jokeFlags.flag"
    })
    Optional<Joke> findById(Long id);

    @EntityGraph(attributePaths = {
            "category",
            "type",
            "language",
            "jokeFlags",
            "jokeFlags.flag",
            "primeraVez",
            "primeraVez.telefonos"
    })
    List<Joke> findAllByOrderByIdAsc();

    @Query("select j from Joke j join fetch j.primeraVez pv join fetch pv.telefonos t order by j.id")
    List<Joke> findAllWithPrimeraVezAndTelefonos();

    List<Joke> findByText1ContainingIgnoreCase(String text);
}
