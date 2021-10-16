package com.baze.skole.mapping.mapper.mjesta;

import com.baze.skole.dto.mjesta.MjestoDTO;
import com.baze.skole.model.mjesta.Mjesto;

public interface MjestaMapper {
    MjestoDTO mapMjestoToDTO(Mjesto mjesto);
}
