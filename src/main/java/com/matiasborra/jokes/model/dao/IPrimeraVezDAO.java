package com.matiasborra.jokes.model.dao;

import com.matiasborra.jokes.model.entity.PrimeraVez;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPrimeraVezDAO extends JpaRepository<PrimeraVez, Long> {
    Optional<PrimeraVez> findByJoke_Id(Long jokeId);
    void deleteByJoke_Id(Long jokeId);
}
