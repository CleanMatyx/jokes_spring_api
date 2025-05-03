package com.matiasborra.jokes.model.dao;

import com.matiasborra.jokes.model.entity.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RepositoryRestResource(path = "flag", collectionResourceRel = "flag")
public interface IFlagDAO extends JpaRepository<Flag, Long> {
    @Query("SELECT f FROM Flag f WHERE f.name = :name")
    Optional<Flag> findByName(@Param("name") String name);
}
