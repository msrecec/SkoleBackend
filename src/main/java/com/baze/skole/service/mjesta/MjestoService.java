package com.baze.skole.service.mjesta;

import com.baze.skole.dto.mjesta.MjestoDTO;

import java.util.List;
import java.util.Optional;

public interface MjestoService {
    Optional<MjestoDTO> findById(Long id);
    List<MjestoDTO> findAll();

}
