package com.baze.skole.mapping.mapper.izvrsitelji;

import com.baze.skole.dto.izvrsitelji.IzvrsiteljDTO;
import com.baze.skole.mapping.mapper.kolegiji.KolegijiMapper;
import com.baze.skole.mapping.mapper.nastavnici.NastavniciMapper;
import com.baze.skole.mapping.mapper.ulogaizvrsitelja.UlogaIzvrsiteljaMapper;
import com.baze.skole.model.izvrsitelji.Izvrsitelj;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IzvrsiteljMapperImpl implements IzvrsiteljMapper {

    private NastavniciMapper nastavniciMapper;
    private KolegijiMapper kolegijiMapper;
    private UlogaIzvrsiteljaMapper ulogaIzvrsiteljaMapper;

    public IzvrsiteljMapperImpl(NastavniciMapper nastavniciMapper, KolegijiMapper kolegijiMapper, UlogaIzvrsiteljaMapper ulogaIzvrsiteljaMapper) {
        this.nastavniciMapper = nastavniciMapper;
        this.kolegijiMapper = kolegijiMapper;
        this.ulogaIzvrsiteljaMapper = ulogaIzvrsiteljaMapper;
    }

    @Override
    public IzvrsiteljDTO mapIzvrsiteljToDTO(Izvrsitelj izvrsitelj) {
        return new IzvrsiteljDTO(
                izvrsitelj.getId(),
                Optional.ofNullable(izvrsitelj.getNastavnik()).map(nastavniciMapper::mapNastavnikToDTO).get(),
                Optional.ofNullable(izvrsitelj.getKolegij()).map(kolegijiMapper::mapKolegijToDTO).get(),
                Optional.ofNullable(izvrsitelj.getUlogaIzvrsitelja()).map(ulogaIzvrsiteljaMapper::mapUlogaIzvrsiteljaToDTO).get()
        );
    }
}
