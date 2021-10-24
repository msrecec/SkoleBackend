package com.baze.skole.service.ocjene;

import com.baze.skole.command.ocjene.OcjenaCommand;
import com.baze.skole.dto.ocjene.OcjenaDTO;
import com.baze.skole.exception.BadRequestException;
import com.baze.skole.exception.InternalServerErrorException;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.model.ocjene.Ocjena;

import java.util.List;
import java.util.Optional;

public interface OcjenaService {
    Optional<OcjenaDTO> findOcjenaByStudentIdAndKolegijId(Long studentId, Long kolegijId) throws ResourceNotFoundException;
    Optional<OcjenaDTO> save(OcjenaCommand command) throws ResourceNotFoundException, InternalServerErrorException;
    Optional<OcjenaDTO> update(OcjenaCommand command) throws ResourceNotFoundException, InternalServerErrorException;
    List<OcjenaDTO> saveOcjene(List<OcjenaCommand> commands) throws BadRequestException, ResourceNotFoundException, InternalServerErrorException;
    void delete(Long idOcjena);
}
