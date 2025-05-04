package com.matiasborra.jokes.model.dao;

import com.matiasborra.jokes.model.entity.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * DAO para la entidad Telefono.
 * Proporciona métodos para realizar operaciones CRUD en la base de datos,
 * incluyendo búsquedas específicas por ID de PrimeraVez.
 *
 * @author Matias Borra
 */
public interface ITelefonoDAO extends JpaRepository<Telefono, Long> {

    /**
     * Busca todas las entidades Telefono asociadas a un ID de PrimeraVez específico.
     *
     * @param primeraVezId ID de la entidad PrimeraVez asociada
     * @return Lista de entidades Telefono asociadas al ID de PrimeraVez
     */
    List<Telefono> findAllByPrimeraVezId(Long primeraVezId);
}