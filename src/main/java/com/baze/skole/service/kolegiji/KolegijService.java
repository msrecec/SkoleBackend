package com.baze.skole.service.kolegiji;

import com.baze.skole.command.kolegiji.KolegijCommand;
import com.baze.skole.command.smjer.SmjerCommand;
import com.baze.skole.dto.kolegiji.KolegijDTO;
import com.baze.skole.exception.InternalServerErrorException;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.model.kolegiji.Kolegij;

import java.util.List;
import java.util.Optional;

public interface KolegijService {
    List<KolegijDTO> findKolegijBySmjer(Long idSmjer) throws ResourceNotFoundException;
    Optional<KolegijDTO> saveKolegijBySmjerId(KolegijCommand command) throws ResourceNotFoundException, InternalServerErrorException;
}
