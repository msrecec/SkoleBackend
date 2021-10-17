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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryJpa studentRepositoryJpa;
    private final MjestaRepositoryJpa mjestaRepositoryJpa;
    private final StudentMapper studentMapper;
    private final Integer MAXIMUM_PAGE_SIZE = 100;

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

        Optional<Mjesto> mjestoPrebivalista = mjestaRepositoryJpa.findMjestoByPostbr(command.getPostBrPrebivalista());

        Optional<Mjesto> mjestoStanovanja = mjestaRepositoryJpa.findMjestoByPostbr(command.getPostBrStanovanja());

        if(mjestoPrebivalista.isEmpty() || mjestoStanovanja.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessageConstants.RESOURCE_NOT_FOUND.getMessage());
        }

        student.setMjestoPrebivalista(mjestoPrebivalista.get());

        student.setMjestoStanovanja(mjestoStanovanja.get());

        student = studentRepositoryJpa.save(student);

        if(student == null) {
            throw new InternalServerError(ErrorMessageConstants.INTERNAL_SERVER_ERROR.getMessage());
        }

        return Optional.of(studentMapper.mapStudentToDTO(student));
    }
}
