package com.baze.skole.mapping.mapper.studenti;

import com.baze.skole.dto.studenti.StudentDTO;
import com.baze.skole.mapping.mapper.mjesta.MjestaMapper;
import com.baze.skole.mapping.mapper.smjerovi.SmjeroviMapper;
import com.baze.skole.model.mjesta.Mjesto;
import com.baze.skole.model.studenti.Student;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentMapperImpl implements StudentMapper{

    private final MjestaMapper mjestaMapper;
    private final SmjeroviMapper smjeroviMapper;

    public StudentMapperImpl(MjestaMapper mjestaMapper, SmjeroviMapper smjeroviMapper) {
        this.mjestaMapper = mjestaMapper;
        this.smjeroviMapper = smjeroviMapper;
    }

    @Override
    public StudentDTO mapStudentToDTO(Student student) {

        return new StudentDTO(student.getId(),
                student.getJmbag(),
                student.getIme(),
                student.getPrezime(),
                student.getDatumUpisa(),
                Optional.ofNullable(student.getMjestoPrebivalista()).map(mjestaMapper::mapMjestoToDTO).get(),
                Optional.ofNullable(student.getMjestoStanovanja()).map(mjestaMapper::mapMjestoToDTO).get(),
                Optional.ofNullable(student.getSmjer()).map(smjeroviMapper::mapSmjerToDTO).get());
    }
}
