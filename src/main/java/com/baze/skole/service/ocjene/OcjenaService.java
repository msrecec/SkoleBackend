package com.baze.skole.service.ocjene;

import com.baze.skole.dto.ocjene.OcjenaDTO;
import com.baze.skole.exception.ResourceNotFoundException;

import java.util.Optional;

public interface OcjenaService {
    Optional<OcjenaDTO> findOcjenaByStudentIdAndKolegijId(Long studentId, Long kolegijId) throws ResourceNotFoundException;
}
