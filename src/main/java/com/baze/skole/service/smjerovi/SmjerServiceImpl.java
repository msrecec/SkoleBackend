package com.baze.skole.service.smjerovi;

import com.baze.skole.dto.smjerovi.SmjerDTO;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.mapping.mapper.smjerovi.SmjeroviMapper;
import com.baze.skole.model.smjerovi.Smjer;
import com.baze.skole.model.ustanove.Ustanova;
import com.baze.skole.repository.smjerovi.SmjerRepositoryJpa;
import com.baze.skole.repository.ustanove.UstanoveRepositoryJpa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SmjerServiceImpl implements SmjerService{

    SmjerRepositoryJpa smjerRepositoryJpa;
    UstanoveRepositoryJpa ustanoveRepositoryJpa;
    private SmjeroviMapper mapper;

    public SmjerServiceImpl(SmjerRepositoryJpa smjerRepositoryJpa, UstanoveRepositoryJpa ustanoveRepositoryJpa, SmjeroviMapper mapper) {
        this.smjerRepositoryJpa = smjerRepositoryJpa;
        this.ustanoveRepositoryJpa = ustanoveRepositoryJpa;
        this.mapper = mapper;
    }

    @Override
    public List<SmjerDTO> findSmjerByUstanovaId(Long idUstanova) throws ResourceNotFoundException {

        List<Smjer> smjerovi = smjerRepositoryJpa.findSmjerByIdUstanove(idUstanova);

        if(smjerovi.isEmpty()) {
            throw new ResourceNotFoundException("ustanova with the given id was not found");
        }

        return smjerovi.stream().map(mapper::mapSmjerToDTO).collect(Collectors.toList());
    }
}
