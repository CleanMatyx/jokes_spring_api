package com.matiasborra.jokes.model.dao;

import com.matiasborra.jokes.model.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * DAO para la entidad Type.
 * Proporciona métodos para realizar operaciones CRUD en la base de datos.
 *
 * @author Matias Borra
 */
@RepositoryRestResource(path = "type", collectionResourceRel = "type")
public interface ITypeDAO extends JpaRepository<Type, Long> {
}