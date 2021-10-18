package com.baze.skole.mapping.mapper.smjerovi;

import com.baze.skole.dto.smjerovi.SmjerDTO;
import com.baze.skole.mapping.mapper.mjesta.MjestaMapper;
import com.baze.skole.mapping.mapper.ustanove.UstanoveMapper;
import com.baze.skole.model.smjerovi.Smjer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SmjeroviMapperImpl implements SmjeroviMapper {

    private UstanoveMapper mapper;

    public SmjeroviMapperImpl(UstanoveMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public SmjerDTO mapSmjerToDTO(Smjer smjer) {
        return SmjerDTO.builder()
                .id(smjer.getId())
                .naziv(smjer.getNaziv())
                .ustanova(Optional.of(smjer.getUstanova()).map(mapper::mapUstanovaToDTO).get())
                .build();
    }
}
