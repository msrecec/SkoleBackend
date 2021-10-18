package com.baze.skole.service.studenti;

import com.baze.skole.command.studenti.StudentCommand;
import com.baze.skole.dto.studenti.StudentDTO;
import com.baze.skole.dto.studenti.StudentDTOPaginated;
import com.baze.skole.exception.BadParamsException;
import com.baze.skole.exception.InternalServerError;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.mapping.mapper.studenti.StudentMapper;
import com.baze.skole.model.error.ErrorMessageConstants;
import com.baze.skole.model.mjesta.Mjesto;
import com.baze.skole.model.studenti.Student;
import com.baze.skole.repository.mjesta.MjestaRepositoryJpa;
import com.baze.skole.repository.studenti.StudentRepositoryJpa;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryJpa studentRepositoryJpa;
    private final MjestaRepositoryJpa mjestaRepositoryJpa;
    private final StudentMapper studentMapper;
    private static final Integer MAXIMUM_PAGE_SIZE = 100;

    public StudentServiceImpl(StudentRepositoryJpa studentRepositoryJpa, MjestaRepositoryJpa mjestaRepositoryJpa, StudentMapper studentMapper) {
        this.studentRepositoryJpa = studentRepositoryJpa;
        this.mjestaRepositoryJpa = mjestaRepositoryJpa;
        this.studentMapper = studentMapper;
    }

    @Override
    public Optional<StudentDTO> findById(Long id) {
        return studentRepositoryJpa.findById(id).map(studentMapper::mapStudentToDTO);
    }

    @Override
    public List<StudentDTO> findAll() throws ResourceNotFoundException {

        List<Student> studenti = studentRepositoryJpa.findAll();

        if(studenti.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessageConstants.RESOURCE_NOT_FOUND.getMessage());
        }

        return studenti.stream().map(studentMapper::mapStudentToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<StudentDTOPaginated> findByPage(Integer page, Integer pageSize) throws BadParamsException, ResourceNotFoundException {

        if(page < 0 || pageSize > MAXIMUM_PAGE_SIZE) {
            throw new BadParamsException(ErrorMessageConstants.BAD_PARAMS.getMessage());
        }

        PageRequest pageRequest = PageRequest.of(page, pageSize);

        List<Student> students = this.studentRepositoryJpa.findAll(pageRequest).getContent();

        if(students.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessageConstants.RESOURCE_NOT_FOUND.getMessage());
        }

        long totalPages = this.studentRepositoryJpa.findAll(pageRequest).getTotalPages();

        long totalElements = this.studentRepositoryJpa.findAll(pageRequest).getTotalElements();

        return Optional.of(new StudentDTOPaginated(students.stream().map(studentMapper::mapStudentToDTO).collect(Collectors.toList()), totalPages, totalElements));
    }

    @Override
    public Optional<StudentDTO> save(StudentCommand command) throws ResourceNotFoundException, InternalServerError {

        Student student = Student.builder()
                .jmbag(command.getJmbag())
                .ime(command.getIme())
                .prezime(command.getPrezime())
                .datumUpisa(command.getDatumUpisa())
                .build();

        List<Mjesto> mjestaPrebivalista = mjestaRepositoryJpa.findMjestoByPostbr(command.getPostBrPrebivalista());

        List<Mjesto> mjestaStanovanja = mjestaRepositoryJpa.findMjestoByPostbr(command.getPostBrStanovanja());

        if(mjestaPrebivalista.isEmpty() || mjestaStanovanja.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessageConstants.RESOURCE_NOT_FOUND.getMessage());
        }

        student.setMjestoPrebivalista(mjestaPrebivalista.get(0));

        student.setMjestoStanovanja(mjestaStanovanja.get(0));

        student = studentRepositoryJpa.save(student);

        if(student == null) {
            throw new InternalServerError(ErrorMessageConstants.INTERNAL_SERVER_ERROR.getMessage());
        }

        return Optional.of(studentMapper.mapStudentToDTO(student));
    }

    @Override
    public Optional<StudentDTO> update(StudentCommand command) throws ResourceNotFoundException, InternalServerError {

        Optional<Student> student = studentRepositoryJpa.findById(command.getId());

        if(student.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessageConstants.RESOURCE_NOT_FOUND.getMessage());
        }

        student.get().setJmbag(command.getJmbag());
        student.get().setIme(command.getIme());
        student.get().setPrezime(command.getPrezime());
        student.get().setDatumUpisa(command.getDatumUpisa());

        List<Mjesto> mjestaPrebivalista = mjestaRepositoryJpa.findMjestoByPostbr(command.getPostBrPrebivalista());

        List<Mjesto> mjestaStanovanja = mjestaRepositoryJpa.findMjestoByPostbr(command.getPostBrStanovanja());

        if(mjestaPrebivalista.isEmpty() || mjestaStanovanja.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessageConstants.RESOURCE_NOT_FOUND.getMessage());
        }

        student.get().setMjestoPrebivalista(mjestaPrebivalista.get(0));

        student.get().setMjestoStanovanja(mjestaStanovanja.get(0));

        student = Optional.ofNullable(studentRepositoryJpa.save(student.get()));

        if(student.isEmpty()) {
            throw new InternalServerError(ErrorMessageConstants.INTERNAL_SERVER_ERROR.getMessage());
        }

        return student.map(studentMapper::mapStudentToDTO);
    }

    @Override
    public List<StudentDTO> findStudentiByIdKolegij(Long idKolegij) throws ResourceNotFoundException {

        List<Student> studenti = studentRepositoryJpa.findStudentByIdKolegij(idKolegij);

        if(studenti.isEmpty()) {
            throw new ResourceNotFoundException("studenti resource was not found");
        }

        return studenti.stream().map(studentMapper::mapStudentToDTO).collect(Collectors.toList());
    }
}
