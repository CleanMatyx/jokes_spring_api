package com.matiasborra.jokes.model.dao;

import com.matiasborra.jokes.model.entity.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO para la entidad Flag.
 * Proporciona métodos para realizar operaciones CRUD en la base de datos.
 *
 * @author Matias Borra
 */
@Repository
public interface IFlagDAO extends JpaRepository<Flag, Long> {
}
