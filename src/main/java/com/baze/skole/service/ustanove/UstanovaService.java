package com.baze.skole.service.ustanove;

import com.baze.skole.command.ustanove.UstanovaCommand;
import com.baze.skole.dto.ustanove.UstanovaDTO;
import com.baze.skole.dto.ustanove.UstanovaDTOPaginated;
import com.baze.skole.exception.BadRequestException;
import com.baze.skole.exception.InternalServerErrorException;
import com.baze.skole.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UstanovaService {
    List<UstanovaDTO> findAll() throws ResourceNotFoundException;
    Optional<UstanovaDTO> findById(Long id) throws ResourceNotFoundException;
    Optional<UstanovaDTO> save(UstanovaCommand command) throws ResourceNotFoundException, InternalServerErrorException;
    Optional<UstanovaDTO> update(UstanovaCommand command) throws ResourceNotFoundException, InternalServerErrorException;
    Optional<UstanovaDTOPaginated> findByPage(Integer page, Integer pageSize) throws BadRequestException, ResourceNotFoundException;
}
