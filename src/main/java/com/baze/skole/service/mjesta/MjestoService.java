package com.baze.skole.service.mjesta;

import com.baze.skole.dto.mjesta.MjestoDTO;
import com.baze.skole.dto.mjesta.MjestoDTOPaginated;
import com.baze.skole.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface MjestoService {
    Optional<MjestoDTO> findById(Long id);
    List<MjestoDTO> findAll();
    Optional<MjestoDTOPaginated> findByPage(Integer page, Integer pageSize) throws ResourceNotFoundException;
}
