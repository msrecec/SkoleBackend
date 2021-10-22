package com.baze.skole.service.ulogaizvrsitelja;

import com.baze.skole.dto.ulogaizvrsitelja.UlogaIzvrsiteljaDTO;
import com.baze.skole.exception.ResourceNotFoundException;

import java.util.Optional;

public interface UlogaIzvrsiteljaService {

    Optional<UlogaIzvrsiteljaDTO> findUlogaIzvrsiteljaByNastavnikId(Long nastavnikId, Long kolegijId) throws ResourceNotFoundException;

}
