package com.baze.skole.mapping.mapper.mjesta;

import com.baze.skole.dto.mjesta.MjestoDTO;
import com.baze.skole.mapping.mapper.zupanije.ZupanijeMapper;
import com.baze.skole.model.mjesta.Mjesto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MjestaMapperImpl implements MjestaMapper{
    ZupanijeMapper zupanijeMapper;

    public MjestaMapperImpl(ZupanijeMapper zupanijeMapper) {
        this.zupanijeMapper = zupanijeMapper;
    }

    @Override
    public MjestoDTO mapMjestoToDTO(Mjesto mjesto) {

        if(mjesto.getZupanija() != null) {
            return new MjestoDTO(mjesto.getId(), mjesto.getPostBr(), mjesto.getNazivMjesta(), zupanijeMapper.mapZupanijaToDTO(mjesto.getZupanija()));
        }

        return new MjestoDTO(mjesto.getId(), mjesto.getPostBr(), mjesto.getNazivMjesta(), null);

    }
}
