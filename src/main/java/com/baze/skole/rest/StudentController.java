package com.baze.skole.rest;

import com.baze.skole.command.studenti.StudentCommand;
import com.baze.skole.dto.studenti.StudentDTO;
import com.baze.skole.dto.studenti.StudentDTOPaginated;
import com.baze.skole.exception.BadRequestException;
import com.baze.skole.exception.InternalServerErrorException;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.service.studenti.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/studenti")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    List<StudentDTO> findAllStudenti() throws ResourceNotFoundException {
        return this.studentService.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<StudentDTO> findStudentById(@PathVariable(name = "id") final Long id) {
        return this.studentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/page")
    public ResponseEntity<StudentDTOPaginated> findStudentByPage(@RequestParam(name = "page") final Integer page,
                                                                 @RequestParam(name = "pageSize") final Integer pageSize) throws BadRequestException, ResourceNotFoundException {
        return this.studentService.findByPage(page, pageSize).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudentDTO> save(@Valid @RequestBody final StudentCommand command) throws ResourceNotFoundException, InternalServerErrorException {
        return studentService.save(command)
                .map(
                        studentDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(studentDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }

    @PutMapping
    public ResponseEntity<StudentDTO> update(@Valid @RequestBody final StudentCommand command) throws ResourceNotFoundException, InternalServerErrorException {
        return this.studentService.update(command)
                .map(
                        studentDTO -> ResponseEntity
                                .status(HttpStatus.OK)
                                .body(studentDTO)
                ).orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }

    @GetMapping("/kolegiji")
    List<StudentDTO> findStudentByIdKolegij(@RequestParam(name = "idKolegij") Long idKolegij) throws ResourceNotFoundException {
        return this.studentService.findStudentiByIdKolegij(idKolegij);
    }

    @GetMapping("/fts")
    List<StudentDTO> fullTextSearchStudenti(@RequestParam(name = "input") String input) throws BadRequestException, ResourceNotFoundException {
        return this.studentService.fullTextSearch(input);
    }


}
