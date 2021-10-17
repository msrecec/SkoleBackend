package com.baze.skole.mapping.mapper.nastavnici;

import com.baze.skole.dto.nastavnici.NastavnikDTO;
import com.baze.skole.model.nastavnici.Nastavnik;

public interface NastavniciMapper {
    NastavnikDTO mapNastavnikToDTO(Nastavnik nastavnik);
}
