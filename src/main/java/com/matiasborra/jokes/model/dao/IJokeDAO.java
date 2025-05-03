package com.matiasborra.jokes.model.dao;

import com.matiasborra.jokes.model.entity.Joke;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface IJokeDAO extends JpaRepository<Joke, Long> {

    @Override
    @EntityGraph(attributePaths = {
            "categories",
            "types",
            "language",
            "jokeFlags",
            "jokeFlags.flag"
    })
    List<Joke> findAll();

    @Override
    @EntityGraph(attributePaths = {
            "categories",
            "types",
            "language",
            "jokeFlags",
            "jokeFlags.flag"
    })
    Optional<Joke> findById(Long id);

    @EntityGraph(attributePaths = {
            "categories",
            "types",
            "language",
            "jokeFlags",
            "jokeFlags.flag"
    })
    List<Joke> findAllByOrderByIdAsc();
}
