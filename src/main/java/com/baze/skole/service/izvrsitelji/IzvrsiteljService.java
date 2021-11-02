package com.baze.skole.service.izvrsitelji;

import com.baze.skole.dto.izvrsitelji.IzvrsiteljDTO;
import com.baze.skole.exception.BadRequestException;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.model.izvrsitelji.Izvrsitelj;

import java.util.Optional;

public interface IzvrsiteljService {
    Optional<IzvrsiteljDTO> findByIdNastavnikAndIdKolegij(Long idNastavnik, Long idKolegij) throws BadRequestException, ResourceNotFoundException;
}
