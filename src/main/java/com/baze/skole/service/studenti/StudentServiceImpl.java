package com.baze.skole.service.studenti;

import com.baze.skole.command.studenti.StudentCommand;
import com.baze.skole.dto.studenti.StudentDTO;
import com.baze.skole.dto.studenti.StudentDTOPaginated;
import com.baze.skole.exception.BadRequestException;
import com.baze.skole.exception.InternalServerErrorException;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.mapping.mapper.studenti.StudentMapper;
import com.baze.skole.model.error.ErrorMessageConstants;
import com.baze.skole.model.mjesta.Mjesto;
import com.baze.skole.model.smjerovi.Smjer;
import com.baze.skole.model.studenti.Student;
import com.baze.skole.repository.mjesta.MjestaRepositoryJpa;
import com.baze.skole.repository.smjerovi.SmjerRepositoryJpa;
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
    private final SmjerRepositoryJpa smjerRepositoryJpa;
    private final StudentMapper studentMapper;
    private static final Integer MAXIMUM_PAGE_SIZE = 100;

    public StudentServiceImpl(StudentRepositoryJpa studentRepositoryJpa, MjestaRepositoryJpa mjestaRepositoryJpa, SmjerRepositoryJpa smjerRepositoryJpa, StudentMapper studentMapper) {
        this.studentRepositoryJpa = studentRepositoryJpa;
        this.mjestaRepositoryJpa = mjestaRepositoryJpa;
        this.smjerRepositoryJpa = smjerRepositoryJpa;
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
    public Optional<StudentDTOPaginated> findByPage(Integer page, Integer pageSize) throws BadRequestException, ResourceNotFoundException {

        if(page < 0 || pageSize > MAXIMUM_PAGE_SIZE) {
            throw new BadRequestException(ErrorMessageConstants.BAD_PARAMS.getMessage());
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
    public Optional<StudentDTO> save(StudentCommand command) throws ResourceNotFoundException, InternalServerErrorException {

        Student student = Student.builder()
                .jmbag(command.getJmbag())
                .ime(command.getIme())
                .prezime(command.getPrezime())
                .datumUpisa(command.getDatumUpisa())
                .build();

        List<Mjesto> mjestaPrebivalista = mjestaRepositoryJpa.findMjestoByPostbr(command.getPostBrPrebivalista());

        List<Mjesto> mjestaStanovanja = mjestaRepositoryJpa.findMjestoByPostbr(command.getPostBrStanovanja());

        Optional<Smjer> smjer = smjerRepositoryJpa.findById(command.getIdSmjer());

        if(smjer.isEmpty()) {
            throw new ResourceNotFoundException("smjer with the given id was not found");
        }

        if(mjestaPrebivalista.isEmpty() || mjestaStanovanja.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessageConstants.RESOURCE_NOT_FOUND.getMessage());
        }

        student.setMjestoPrebivalista(mjestaPrebivalista.get(0));

        student.setMjestoStanovanja(mjestaStanovanja.get(0));

        student.setSmjer(smjer.get());

        student = studentRepositoryJpa.save(student);

        if(student == null) {
            throw new InternalServerErrorException(ErrorMessageConstants.INTERNAL_SERVER_ERROR.getMessage());
        }

        return Optional.of(studentMapper.mapStudentToDTO(student));
    }

    @Override
    public Optional<StudentDTO> update(StudentCommand command) throws ResourceNotFoundException, InternalServerErrorException {

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
            throw new InternalServerErrorException(ErrorMessageConstants.INTERNAL_SERVER_ERROR.getMessage());
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

    @Override
    public Optional<StudentDTOPaginated> fullTextSearch(String input, Integer page, Integer pageSize) throws BadRequestException, ResourceNotFoundException {
        long totalPages;
        long totalElements;

        if(page < 0 || pageSize > 100) {
            throw new BadRequestException("page must be larger than 0 and pageSize must be smaller or equals to 100");
        }

        PageRequest pageRequest = PageRequest.of(page, pageSize);

        List<Student> studenti;

        String[] split = input.split(" ");

        if(split.length == 0) {
            throw new BadRequestException("the input string provided is invalid");
        } else if(split.length == 1) {
            studenti = studentRepositoryJpa.ftsStudents(split[0], pageRequest).getContent();
            totalPages = studentRepositoryJpa.ftsStudents(split[0], pageRequest).getTotalPages();
            totalElements = studentRepositoryJpa.ftsStudents(split[0], pageRequest).getTotalElements();
        } else if(split.length == 2) {
            studenti = studentRepositoryJpa.ftsStudents(split[0] + " " + split[1], pageRequest).getContent();
            totalPages = studentRepositoryJpa.ftsStudents(split[0] + " " + split[1], pageRequest).getTotalPages();
            totalElements = studentRepositoryJpa.ftsStudents(split[0] + " " + split[1], pageRequest).getTotalElements();
        } else if(split.length == 3) {
            studenti = studentRepositoryJpa.ftsStudents(split[0] + " " + split[1] + " " + split[2], pageRequest).getContent();
            totalPages = studentRepositoryJpa.ftsStudents(split[0] + " " + split[1] + " " + split[2], pageRequest).getTotalPages();
            totalElements = studentRepositoryJpa.ftsStudents(split[0] + " " + split[1] + " " + split[2], pageRequest).getTotalElements();
        } else {
            throw new BadRequestException("the provided string has more than 3 words");
        }

        if(studenti.isEmpty()) {
            throw new ResourceNotFoundException("no students were found");
        }

        return Optional.of(new StudentDTOPaginated(studenti.stream().map(studentMapper::mapStudentToDTO).collect(Collectors.toList()), totalPages, totalElements));
    }

    @Override
    public Optional<StudentDTOPaginated> findStudentiByIdSmjerPaginated(Long idSmjer, Long idKolegij, Integer page, Integer pageSize) throws ResourceNotFoundException {
        long totalPages;
        long totalElements;

        PageRequest pageRequest = PageRequest.of(page, pageSize);

        List<Student> studenti = studentRepositoryJpa.findStudentByIdSmjerPaginated(idSmjer, idKolegij, pageRequest).getContent();

        if(studenti.isEmpty()) {
            throw new ResourceNotFoundException("studenti resource was not found");
        }

        totalPages = studentRepositoryJpa.findStudentByIdSmjerPaginated(idSmjer, idKolegij, pageRequest).getTotalPages();

        totalElements = studentRepositoryJpa.findStudentByIdSmjerPaginated(idSmjer, idKolegij, pageRequest).getTotalElements();

        return Optional.of(new StudentDTOPaginated(studenti.stream().map(studentMapper::mapStudentToDTO).collect(Collectors.toList()), totalPages, totalElements));
    }
}
