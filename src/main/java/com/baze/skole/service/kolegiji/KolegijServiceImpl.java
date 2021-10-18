package com.baze.skole.service.kolegiji;

import com.baze.skole.dto.kolegiji.KolegijDTO;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.mapping.mapper.kolegiji.KolegijiMapper;
import com.baze.skole.model.kolegiji.Kolegij;
import com.baze.skole.repository.kolegiji.KolegijRepositoryJpa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KolegijServiceImpl implements KolegijService{

    private final KolegijRepositoryJpa kolegijRepositoryJpa;
    private final KolegijiMapper kolegijiMapper;

    public KolegijServiceImpl(KolegijRepositoryJpa kolegijRepositoryJpa, KolegijiMapper kolegijiMapper) {
        this.kolegijRepositoryJpa = kolegijRepositoryJpa;
        this.kolegijiMapper = kolegijiMapper;
    }

    @Override
    public List<KolegijDTO> findKolegijBySmjer(Long idSmjer) throws ResourceNotFoundException {

        List<Kolegij> kolegiji = kolegijRepositoryJpa.findKolegijiByIdSmjera(idSmjer);

        if(kolegiji.isEmpty()) {
            throw new ResourceNotFoundException("kolegiji were not found");
        }

        return kolegiji.stream().map(kolegijiMapper::mapKolegijToDTO).collect(Collectors.toList());
    }
}
