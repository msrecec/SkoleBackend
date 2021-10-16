package com.baze.skole.mapping.mapper.zupanije;

import com.baze.skole.dto.zupanije.ZupanijaDTO;
import com.baze.skole.model.zupanije.Zupanija;

public interface ZupanijeMapper {
    ZupanijaDTO mapZupanijaToDTO(Zupanija zupanija);
}
