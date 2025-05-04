package com.matiasborra.jokes.model.dao;

import com.matiasborra.jokes.model.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * DAO para la entidad Language.
 * Proporciona métodos para realizar operaciones CRUD en la base de datos.
 *
 * @author Matias Borra
 */
@RepositoryRestResource(path = "language", collectionResourceRel = "language")
public interface ILanguageDAO extends JpaRepository<Language, Long> {}