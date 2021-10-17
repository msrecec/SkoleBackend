package com.baze.skole.mapping.mapper.nastavnici;

import com.baze.skole.dto.nastavnici.NastavnikDTO;
import com.baze.skole.mapping.mapper.mjesta.MjestaMapper;
import com.baze.skole.model.nastavnici.Nastavnik;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NastavniciMapperImpl implements NastavniciMapper{

    private final MjestaMapper mapper;

    public NastavniciMapperImpl(MjestaMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public NastavnikDTO mapNastavnikToDTO(Nastavnik nastavnik) {
        return new NastavnikDTO(nastavnik.getId(),nastavnik.getJmbg(), nastavnik.getIme(), nastavnik.getPrezime(), nastavnik.getAdresa(), nastavnik.getTitulaIspred(),
        nastavnik.getTitulaIza(), Optional.ofNullable(nastavnik.getMjestoPrebivalista()).map(mapper::mapMjestoToDTO).get());
    }

}
