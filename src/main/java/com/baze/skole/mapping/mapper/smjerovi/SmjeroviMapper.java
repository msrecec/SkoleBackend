package com.baze.skole.mapping.mapper.smjerovi;

import com.baze.skole.dto.smjerovi.SmjerDTO;
import com.baze.skole.model.smjerovi.Smjer;

public interface SmjeroviMapper {
    SmjerDTO mapSmjerToDTO(Smjer smjer);
}
