package com.matiasborra.jokes.model.dao;

import com.matiasborra.jokes.model.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeDAO extends JpaRepository<Type, Long> {}
