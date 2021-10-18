package com.baze.skole.mapping.mapper.ulogaizvrsitelja;

import com.baze.skole.dto.ulogaizvrsitelja.UlogaIzvrsiteljaDTO;
import com.baze.skole.model.ulogaizvrsitelja.UlogaIzvrsitelja;
import org.springframework.stereotype.Component;

@Component
public class UlogaIzvrsiteljaMapperImpl implements UlogaIzvrsiteljaMapper {

    @Override
    public UlogaIzvrsiteljaDTO mapUlogaIzvrsiteljaToDTO(UlogaIzvrsitelja ulogaIzvrsitelja) {
        return new UlogaIzvrsiteljaDTO(ulogaIzvrsitelja.getId(), ulogaIzvrsitelja.getNaziv());
    }
}
