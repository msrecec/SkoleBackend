package com.baze.skole.service.ocjene;

import com.baze.skole.dto.ocjene.OcjenaDTO;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.mapping.mapper.ocjene.OcjeneMapper;
import com.baze.skole.model.ocjene.Ocjena;
import com.baze.skole.repository.ocjene.OcjenaRepositoryJpa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OcjenaServiceImpl implements OcjenaService{

    private final OcjenaRepositoryJpa ocjenaRepositoryJpa;
    private final OcjeneMapper ocjeneMapper;

    public OcjenaServiceImpl(OcjenaRepositoryJpa ocjenaRepositoryJpa, OcjeneMapper ocjeneMapper) {
        this.ocjenaRepositoryJpa = ocjenaRepositoryJpa;
        this.ocjeneMapper = ocjeneMapper;
    }

    @Override
    public Optional<OcjenaDTO> findOcjenaByStudentIdAndKolegijId(Long studentId, Long kolegijId) throws ResourceNotFoundException {

        List<Ocjena> ocjene = ocjenaRepositoryJpa.findOcjenaByIdStudent(studentId, kolegijId);

        if(ocjene.isEmpty()) {
            throw new ResourceNotFoundException("ocjena for the given student id and kolegij id does not exist");
        }

        return Optional.of(ocjene.get(0)).map(ocjeneMapper::mapOcjenaToDTO);
    }
}
