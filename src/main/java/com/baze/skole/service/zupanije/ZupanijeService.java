package com.baze.skole.service.zupanije;

import com.baze.skole.dto.zupanije.ZupanijaDTO;

import java.util.List;
import java.util.Optional;

public interface ZupanijeService {
    List<ZupanijaDTO> findAll();
    Optional<ZupanijaDTO> findById(Long id);
}
