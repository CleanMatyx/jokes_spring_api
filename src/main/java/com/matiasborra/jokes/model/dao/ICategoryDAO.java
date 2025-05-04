package com.matiasborra.jokes.model.dao;

import com.matiasborra.jokes.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * DAO para la entidad Category.
 * Proporciona métodos para realizar operaciones CRUD en la base de datos.
 *
 * @author Matias Borra
 */
@RepositoryRestResource(path = "category", collectionResourceRel = "category")
public interface ICategoryDAO extends JpaRepository<Category, Long> {}