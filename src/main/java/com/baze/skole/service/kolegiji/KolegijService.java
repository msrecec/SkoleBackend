package com.baze.skole.service.kolegiji;

import com.baze.skole.dto.kolegiji.KolegijDTO;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.model.kolegiji.Kolegij;

import java.util.List;

public interface KolegijService {
    List<KolegijDTO> findKolegijBySmjer(Long idSmjer) throws ResourceNotFoundException;
}
