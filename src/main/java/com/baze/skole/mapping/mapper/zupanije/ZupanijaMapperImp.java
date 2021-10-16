package com.baze.skole.mapping.mapper.zupanije;

import com.baze.skole.dto.zupanije.ZupanijaDTO;
import com.baze.skole.model.zupanije.Zupanija;
import org.springframework.stereotype.Component;

@Component
public class ZupanijaMapperImp implements ZupanijeMapper{

    @Override
    public ZupanijaDTO mapZupanijaToDTO(Zupanija zupanija) {
        return new ZupanijaDTO(zupanija.getId(), zupanija.getNazivZupanija());
    }
}
