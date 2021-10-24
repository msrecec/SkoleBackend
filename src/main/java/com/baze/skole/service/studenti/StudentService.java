package com.baze.skole.service.studenti;

import com.baze.skole.command.studenti.StudentCommand;
import com.baze.skole.dto.studenti.StudentDTO;
import com.baze.skole.dto.studenti.StudentDTOPaginated;
import com.baze.skole.exception.BadRequestException;
import com.baze.skole.exception.InternalServerErrorException;
import com.baze.skole.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Optional<StudentDTO> findById(Long id);
    List<StudentDTO> findAll() throws ResourceNotFoundException;
    Optional<StudentDTOPaginated> findByPage(Integer page, Integer pageSize) throws BadRequestException, ResourceNotFoundException;
    Optional<StudentDTO> save(StudentCommand command) throws ResourceNotFoundException, InternalServerErrorException;
    Optional<StudentDTO> update(StudentCommand command) throws ResourceNotFoundException, InternalServerErrorException;
    List<StudentDTO> findStudentiByIdKolegij(Long idKolegij) throws ResourceNotFoundException;
    Optional<StudentDTOPaginated> fullTextSearch(String input, Integer page, Integer pageSize) throws BadRequestException, ResourceNotFoundException;
    Optional<StudentDTOPaginated> findStudentiByIdSmjerPaginated(Long idSmjer, Long idKolegij, Integer page, Integer pageSize) throws ResourceNotFoundException;
}
