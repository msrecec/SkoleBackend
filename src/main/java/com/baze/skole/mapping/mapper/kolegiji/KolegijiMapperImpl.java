package com.baze.skole.mapping.mapper.kolegiji;

import com.baze.skole.dto.kolegiji.KolegijDTO;
import com.baze.skole.mapping.mapper.smjerovi.SmjeroviMapper;
import com.baze.skole.model.kolegiji.Kolegij;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KolegijiMapperImpl implements KolegijiMapper{

    SmjeroviMapper mapper;

    public KolegijiMapperImpl(SmjeroviMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public KolegijDTO mapKolegijToDTO(Kolegij kolegij) {
        return new KolegijDTO(kolegij.getId(), kolegij.getNaziv(), kolegij.getOpis(), Optional.of(kolegij.getSmjer()).map(mapper::mapSmjerToDTO).get());
    }
}
