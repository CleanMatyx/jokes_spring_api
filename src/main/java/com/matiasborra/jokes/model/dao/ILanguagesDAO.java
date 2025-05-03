package com.matiasborra.jokes.model.dao;

import com.matiasborra.jokes.model.entity.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "languages", collectionResourceRel = "languages")
public interface ILanguagesDAO extends JpaRepository<Languages, Long> { }