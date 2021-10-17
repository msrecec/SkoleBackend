package com.baze.skole.repository.studenti;

import com.baze.skole.model.studenti.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositoryJpa extends JpaRepository<Student, Long> {
}
