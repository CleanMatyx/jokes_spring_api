package com.matiasborra.jokes.model.services;

import com.matiasborra.jokes.dto.FlagDTO;
import com.matiasborra.jokes.model.dao.IFlagDAO;
import com.matiasborra.jokes.model.entity.Flag;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FlagServiceImpl implements IFlagService {

    private final IFlagDAO flagRepo;
    private final ModelMapper mapper;

    public FlagServiceImpl(IFlagDAO flagRepo, ModelMapper mapper) {
        this.flagRepo = flagRepo;
        this.mapper   = mapper;
    }

    @Override
    public List<FlagDTO> findAll() {
        return flagRepo.findAll()
                .stream()
                .map(f -> mapper.map(f, FlagDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FlagDTO findById(Long id) {
        Flag f = flagRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Flag not found"));
        return mapper.map(f, FlagDTO.class);
    }

    @Override
    public FlagDTO create(FlagDTO dto) {
        Flag f = new Flag();
        f.setFlag(dto.getFlag());
        Flag saved = flagRepo.save(f);
        return mapper.map(saved, FlagDTO.class);
    }

    @Override
    public FlagDTO update(Long id, FlagDTO dto) {
        Flag f = flagRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Flag not found"));
        f.setFlag(dto.getFlag());
        Flag saved = flagRepo.save(f);
        return mapper.map(saved, FlagDTO.class);
    }

    @Override
    public void delete(Long id) {
        flagRepo.deleteById(id);
    }
}
