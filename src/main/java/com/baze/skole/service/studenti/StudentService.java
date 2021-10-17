package com.baze.skole.service.studenti;

import com.baze.skole.command.studenti.StudentCommand;
import com.baze.skole.dto.studenti.StudentDTO;
import com.baze.skole.dto.studenti.StudentDTOPaginated;
import com.baze.skole.exception.BadParamsException;
import com.baze.skole.exception.InternalServerError;
import com.baze.skole.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Optional<StudentDTO> findById(Long id);
    List<StudentDTO> findAll() throws ResourceNotFoundException;
    Optional<StudentDTOPaginated> findByPage(Integer page, Integer pageSize) throws BadParamsException, ResourceNotFoundException;
    Optional<StudentDTO> save(StudentCommand command) throws ResourceNotFoundException, InternalServerError;
}
