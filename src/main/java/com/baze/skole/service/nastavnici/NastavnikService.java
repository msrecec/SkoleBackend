package com.baze.skole.service.nastavnici;

import com.baze.skole.command.nastavnici.NastavnikCommand;
import com.baze.skole.dto.nastavnici.NastavnikDTO;
import com.baze.skole.dto.nastavnici.NastavnikDTOPaginated;
import com.baze.skole.exception.BadRequestException;
import com.baze.skole.exception.InternalServerErrorException;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.model.nastavnici.Nastavnik;

import java.util.List;
import java.util.Optional;

public interface NastavnikService {
    Optional<NastavnikDTO> findById(Long id);
    List<NastavnikDTO> findAll() throws ResourceNotFoundException;
    Optional<NastavnikDTOPaginated> findByPage(Integer page, Integer pageSize) throws BadRequestException, ResourceNotFoundException;
    Optional<NastavnikDTO> save(NastavnikCommand command) throws ResourceNotFoundException, InternalServerErrorException;
    Optional<NastavnikDTO> update(NastavnikCommand command) throws ResourceNotFoundException;
    List<NastavnikDTO> findByIdKolegij(Long idKolegij) throws ResourceNotFoundException;
    List<NastavnikDTO> ftsNastavnici(String input) throws BadRequestException, ResourceNotFoundException;
}
