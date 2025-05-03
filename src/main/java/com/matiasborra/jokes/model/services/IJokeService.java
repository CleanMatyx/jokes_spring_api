package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.JokeDTO;

import java.util.List;

public interface IJokeService {
    List<JokeDTO> findAll();
    JokeDTO findById(Long id);
    JokeDTO create(JokeDTO dto);
    JokeDTO update(Long id, JokeDTO dto);
    void delete(Long id);
    List<JokeDTO> findAllWithPV();
    List<JokeDTO> filterByText(String q);
}
