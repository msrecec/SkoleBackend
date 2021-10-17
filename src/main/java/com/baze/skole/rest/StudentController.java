package com.baze.skole.rest;

import com.baze.skole.dto.studenti.StudentDTO;
import com.baze.skole.dto.studenti.StudentDTOPaginated;
import com.baze.skole.exception.BadParamsException;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.service.studenti.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                                                                 @RequestParam(name = "pageSize") final Integer pageSize) throws BadParamsException, ResourceNotFoundException {
        return this.studentService.findByPage(page, pageSize).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
