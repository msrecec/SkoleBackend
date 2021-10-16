package com.baze.skole.service.zupanije;

import com.baze.skole.dto.zupanije.ZupanijaDTO;
import com.baze.skole.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ZupanijeService {
    List<ZupanijaDTO> findAll() throws ResourceNotFoundException;
    Optional<ZupanijaDTO> findById(Long id);
}
