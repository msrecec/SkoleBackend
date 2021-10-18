package com.baze.skole.service.smjerovi;

import com.baze.skole.dto.smjerovi.SmjerDTO;
import com.baze.skole.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface SmjerService {
    List<SmjerDTO> findSmjerByUstanovaId(Long idUstanova) throws ResourceNotFoundException;
}
