package com.baze.skole.service.mjesta;

import com.baze.skole.dto.mjesta.MjestoDTO;
import com.baze.skole.mapping.mapper.mjesta.MjestaMapper;
import com.baze.skole.repository.mjesta.MjestaRepositoryJpa;
import com.baze.skole.repository.zupanije.ZupanijeRepositoryJpa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MjestoServiceImpl implements MjestoService{

    private MjestaRepositoryJpa mjestaRepositoryJpa;
    private MjestaMapper mapper;

    public MjestoServiceImpl(MjestaRepositoryJpa mjestaRepositoryJpa, MjestaMapper mapper) {
        this.mjestaRepositoryJpa = mjestaRepositoryJpa;
        this.mapper = mapper;
    }

    @Override
    public Optional<MjestoDTO> findById(Long id) {
        return mjestaRepositoryJpa.findById(id).map(mapper::mapMjestoToDTO);
    }

    @Override
    public List<MjestoDTO> findAll() {
        return mjestaRepositoryJpa.findAll().stream().map(mapper::mapMjestoToDTO).collect(Collectors.toList());
    }
}
