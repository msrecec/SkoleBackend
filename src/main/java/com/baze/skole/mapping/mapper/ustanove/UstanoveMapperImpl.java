package com.baze.skole.mapping.mapper.ustanove;

import com.baze.skole.dto.ustanove.UstanovaDTO;
import com.baze.skole.mapping.mapper.mjesta.MjestaMapper;
import com.baze.skole.model.ustanove.Ustanova;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UstanoveMapperImpl implements UstanoveMapper{

    private final MjestaMapper mapper;

    public UstanoveMapperImpl(MjestaMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UstanovaDTO mapUstanovaToDTO(Ustanova ustanova) {
        return UstanovaDTO.builder()
                .id(ustanova.getId())
                .oib(ustanova.getOib())
                .adresa(ustanova.getAdresa())
                .naziv(ustanova.getNaziv())
                .ziroRacun(ustanova.getZiroRacun())
                .datumOsnutka(ustanova.getDatumOsnutka())
                .mjesto(Optional.ofNullable(ustanova.getMjesto()).map(mapper::mapMjestoToDTO).get())
                .build();
    }
}
