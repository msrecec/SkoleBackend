package com.baze.skole.service.ustanove;

import com.baze.skole.command.ustanove.UstanovaCommand;
import com.baze.skole.dto.ustanove.UstanovaDTO;

import java.util.List;
import java.util.Optional;

public interface UstanovaService {
    List<UstanovaDTO> findAll();
    Optional<UstanovaDTO> findById(Long id);
    Optional<UstanovaDTO> save(UstanovaCommand command);
    Optional<UstanovaDTO> update(UstanovaCommand command);
}
