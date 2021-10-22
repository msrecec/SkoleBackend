package com.baze.skole.service.ocjene;

import com.baze.skole.command.ocjene.OcjenaCommand;
import com.baze.skole.dto.ocjene.OcjenaDTO;
import com.baze.skole.exception.InternalServerErrorException;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.mapping.mapper.ocjene.OcjeneMapper;
import com.baze.skole.model.kolegiji.Kolegij;
import com.baze.skole.model.ocjene.Ocjena;
import com.baze.skole.model.studenti.Student;
import com.baze.skole.repository.kolegiji.KolegijRepositoryJpa;
import com.baze.skole.repository.ocjene.OcjenaRepositoryJpa;
import com.baze.skole.repository.studenti.StudentRepositoryJpa;
import org.hibernate.type.OffsetDateTimeType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class OcjenaServiceImpl implements OcjenaService{

    private final OcjenaRepositoryJpa ocjenaRepositoryJpa;
    private final KolegijRepositoryJpa kolegijRepositoryJpa;
    private final StudentRepositoryJpa studentRepositoryJpa;
    private final OcjeneMapper ocjeneMapper;

    public OcjenaServiceImpl(OcjenaRepositoryJpa ocjenaRepositoryJpa, KolegijRepositoryJpa kolegijRepositoryJpa, StudentRepositoryJpa studentRepositoryJpa, OcjeneMapper ocjeneMapper) {
        this.ocjenaRepositoryJpa = ocjenaRepositoryJpa;
        this.kolegijRepositoryJpa = kolegijRepositoryJpa;
        this.studentRepositoryJpa = studentRepositoryJpa;
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

    @Override
    public Optional<OcjenaDTO> save(OcjenaCommand command) throws ResourceNotFoundException, InternalServerErrorException {

        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ISO_LOCAL_TIME)
                .parseDefaulting(ChronoField.EPOCH_DAY, 0)
                .toFormatter();

        LocalDateTime localDateTime = LocalDateTime.parse(command.getVrijemePolaganja(), fmt);

        Ocjena ocjena = Ocjena.builder()
                .ocjena(command.getOcjena())
                .datumPolaganja(command.getDatumPolaganja())
                .vrijemePolaganja(OffsetDateTime.of(localDateTime, ZoneOffset.UTC)).build();

        Optional<Kolegij> kolegij = kolegijRepositoryJpa.findById(command.getKolegijId());

        if(kolegij.isEmpty()) {
            throw new ResourceNotFoundException("kolegij with the given id was not found");
        }

        Optional<Student> student = studentRepositoryJpa.findById(command.getStudentId());

        if(student.isEmpty()) {
            throw new ResourceNotFoundException("student with the given id was not found");
        }

        ocjena.setKolegij(kolegij.get());
        ocjena.setStudent(student.get());

        ocjena = ocjenaRepositoryJpa.save(ocjena);

        if(ocjena == null) {
            throw new InternalServerErrorException("there was an error on the server");
        }

        return Optional.of(ocjena).map(ocjeneMapper::mapOcjenaToDTO);
    }
}
