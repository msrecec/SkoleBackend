package com.baze.skole.service.nastavnici;

import com.baze.skole.command.nastavnici.NastavnikCommand;
import com.baze.skole.dto.nastavnici.NastavnikDTO;
import com.baze.skole.dto.nastavnici.NastavnikDTOPaginated;
import com.baze.skole.exception.BadParamsException;
import com.baze.skole.exception.InternalServerError;
import com.baze.skole.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface NastavnikService {
    Optional<NastavnikDTO> findById(Long id);
    List<NastavnikDTO> findAll() throws ResourceNotFoundException;
    Optional<NastavnikDTOPaginated> findByPage(Integer page, Integer pageSize) throws BadParamsException, ResourceNotFoundException;
    Optional<NastavnikDTO> save(NastavnikCommand command) throws ResourceNotFoundException, InternalServerError;
    Optional<NastavnikDTO> update(NastavnikCommand command) throws ResourceNotFoundException;
    List<NastavnikDTO> findByIdKolegij(Long idKolegij) throws ResourceNotFoundException;
}
