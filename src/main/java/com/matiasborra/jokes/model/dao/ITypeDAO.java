package com.matiasborra.jokes.model.dao;

import com.matiasborra.jokes.model.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "type", collectionResourceRel = "type")
public interface ITypeDAO extends JpaRepository<Type, Long> {}
