package com.baze.skole.service.ustanove;

import com.baze.skole.command.ustanove.UstanovaCommand;
import com.baze.skole.dto.ustanove.UstanovaDTO;
import com.baze.skole.repository.ustanove.UstanoveRepositoryJpa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UstanovaServiceImpl implements UstanovaService{

    private UstanoveRepositoryJpa ustanoveRepositoryJpa;
    private static final Integer MAXIMUM_PAGE_SIZE = 100;

    public UstanovaServiceImpl(UstanoveRepositoryJpa ustanoveRepositoryJpa) {
        this.ustanoveRepositoryJpa = ustanoveRepositoryJpa;
    }

    @Override
    public List<UstanovaDTO> findAll() {
        return null;
    }

    @Override
    public Optional<UstanovaDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<UstanovaDTO> save(UstanovaCommand command) {
        return Optional.empty();
    }

    @Override
    public Optional<UstanovaDTO> update(UstanovaCommand command) {
        return Optional.empty();
    }
}
