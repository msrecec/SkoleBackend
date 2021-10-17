package com.baze.skole.mapping.mapper.studenti;

import com.baze.skole.dto.studenti.StudentDTO;
import com.baze.skole.model.studenti.Student;

public interface StudentMapper {
    StudentDTO mapStudentToDTO(Student student);
}
