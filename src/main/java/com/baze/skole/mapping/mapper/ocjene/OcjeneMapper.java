package com.baze.skole.mapping.mapper.ocjene;

import com.baze.skole.dto.ocjene.OcjenaDTO;
import com.baze.skole.model.ocjene.Ocjena;

public interface OcjeneMapper {
    OcjenaDTO mapOcjenaToDTO(Ocjena ocjena);
}
