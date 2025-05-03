package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.FlagDTO;
import java.util.List;

public interface IFlagService {
    List<FlagDTO>  findAll();
    FlagDTO        findById(Long id);
    FlagDTO        create(FlagDTO dto);
    FlagDTO        update(Long id, FlagDTO dto);
    void           delete(Long id);
}
