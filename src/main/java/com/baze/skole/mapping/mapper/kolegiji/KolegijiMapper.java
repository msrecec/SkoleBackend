package com.baze.skole.mapping.mapper.kolegiji;

import com.baze.skole.dto.kolegiji.KolegijDTO;
import com.baze.skole.model.kolegiji.Kolegij;

public interface KolegijiMapper {
    KolegijDTO mapKolegijToDTO(Kolegij kolegij);
}
