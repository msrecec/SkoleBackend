package com.baze.skole.mapping.mapper.izvrsitelji;

import com.baze.skole.dto.izvrsitelji.IzvrsiteljDTO;
import com.baze.skole.model.izvrsitelji.Izvrsitelj;

public interface IzvrsiteljMapper {
    IzvrsiteljDTO mapIzvrsiteljToDTO(Izvrsitelj izvrsitelj);
}
