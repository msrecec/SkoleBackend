package com.baze.skole.service.nastavnici;

import com.baze.skole.dto.nastavnici.NastavnikDTO;
import com.baze.skole.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface NastavnikService {
    Optional<NastavnikDTO> findById(Long id);
    List<NastavnikDTO> findAll() throws ResourceNotFoundException;
    Optional<NastavnikDTO> findByPage(Integer page, Integer pageSize);
}
