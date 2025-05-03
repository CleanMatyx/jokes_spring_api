package com.matiasborra.jokes.model.dao;

import com.matiasborra.jokes.model.entity.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITelefonoDAO extends JpaRepository<Telefono,Long> {
    List<Telefono> findAllByPrimeraVezId(Long primeraVezId);
}