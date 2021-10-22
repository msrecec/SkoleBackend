package com.baze.skole.service.ulogaizvrsitelja;

import com.baze.skole.dto.ulogaizvrsitelja.UlogaIzvrsiteljaDTO;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.mapping.mapper.ulogaizvrsitelja.UlogaIzvrsiteljaMapper;
import com.baze.skole.model.ulogaizvrsitelja.UlogaIzvrsitelja;
import com.baze.skole.repository.ulogaizvrsitelja.UlogaIzvrsiteljaRepositoryJpa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UlogaIzvrsiteljaServiceImpl implements UlogaIzvrsiteljaService {

    private final UlogaIzvrsiteljaRepositoryJpa ulogaIzvrsiteljaRepositoryJpa;
    private final UlogaIzvrsiteljaMapper ulogaIzvrsiteljaMapper;

    public UlogaIzvrsiteljaServiceImpl(UlogaIzvrsiteljaRepositoryJpa ulogaIzvrsiteljaRepositoryJpa, UlogaIzvrsiteljaMapper ulogaIzvrsiteljaMapper) {
        this.ulogaIzvrsiteljaRepositoryJpa = ulogaIzvrsiteljaRepositoryJpa;
        this.ulogaIzvrsiteljaMapper = ulogaIzvrsiteljaMapper;
    }

    @Override
    public Optional<UlogaIzvrsiteljaDTO> findUlogaIzvrsiteljaByNastavnikId(Long nastavnikId, Long kolegijId) throws ResourceNotFoundException {

        List<UlogaIzvrsitelja> ulogaIzvrsitelja = ulogaIzvrsiteljaRepositoryJpa.findUlogaIzvrsiteljaByNastavnikId(nastavnikId, kolegijId);

        if(ulogaIzvrsitelja.isEmpty()) {
            throw new ResourceNotFoundException("uloga izvrsitelja resource was not found");
        }

        return Optional.of(ulogaIzvrsitelja.get(0)).map(ulogaIzvrsiteljaMapper::mapUlogaIzvrsiteljaToDTO);
    }
}
