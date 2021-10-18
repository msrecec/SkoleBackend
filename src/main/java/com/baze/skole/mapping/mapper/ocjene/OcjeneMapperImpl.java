package com.baze.skole.mapping.mapper.ocjene;

import com.baze.skole.dto.ocjene.OcjenaDTO;
import com.baze.skole.dto.studenti.StudentDTO;
import com.baze.skole.mapping.mapper.kolegiji.KolegijiMapper;
import com.baze.skole.mapping.mapper.studenti.StudentMapper;
import com.baze.skole.model.ocjene.Ocjena;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OcjeneMapperImpl implements OcjeneMapper {

    private final StudentMapper studentMapper;
    private final KolegijiMapper kolegijiMapper;

    public OcjeneMapperImpl(StudentMapper studentMapper, KolegijiMapper kolegijiMapper) {
        this.studentMapper = studentMapper;
        this.kolegijiMapper = kolegijiMapper;
    }

    @Override
    public OcjenaDTO mapOcjenaToDTO(Ocjena ocjena) {
        return new OcjenaDTO(ocjena.getId(),
                ocjena.getDatumPolaganja(),
                ocjena.getVrijemePolaganja(),
                ocjena.getOcjena(),
                Optional.of(ocjena.getStudent()).map(studentMapper::mapStudentToDTO).get(),
                Optional.of(ocjena.getKolegij()).map(kolegijiMapper::mapKolegijToDTO).get());
    }
}
