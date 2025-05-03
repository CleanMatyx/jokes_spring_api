package com.matiasborra.jokes.model.dao;

import com.matiasborra.jokes.model.entity.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFlagDAO extends JpaRepository<Flag, Long> {
}
