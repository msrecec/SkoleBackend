package com.baze.skole.service.ustanove;

import com.baze.skole.command.ustanove.UstanovaCommand;
import com.baze.skole.dto.ustanove.UstanovaDTO;
import com.baze.skole.exception.InternalServerError;
import com.baze.skole.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UstanovaService {
    List<UstanovaDTO> findAll() throws ResourceNotFoundException;
    Optional<UstanovaDTO> findById(Long id) throws ResourceNotFoundException;
    Optional<UstanovaDTO> save(UstanovaCommand command) throws ResourceNotFoundException, InternalServerError;
    Optional<UstanovaDTO> update(UstanovaCommand command) throws ResourceNotFoundException, InternalServerError;
}
