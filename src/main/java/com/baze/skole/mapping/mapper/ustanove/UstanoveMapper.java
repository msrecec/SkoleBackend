package com.baze.skole.mapping.mapper.ustanove;

import com.baze.skole.dto.ustanove.UstanovaDTO;
import com.baze.skole.model.ustanove.Ustanova;

public interface UstanoveMapper {
    UstanovaDTO mapUstanovaToDTO(Ustanova ustanova);
}
