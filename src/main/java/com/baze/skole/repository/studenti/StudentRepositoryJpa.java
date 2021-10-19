package com.baze.skole.repository.studenti;

import com.baze.skole.model.mjesta.Mjesto;
import com.baze.skole.model.studenti.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepositoryJpa extends JpaRepository<Student, Long> {
    @Query(value = "select * from studenti inner join ocjene on studenti.id = ocjene.id_student inner join kolegiji on ocjene.id_kolegij = kolegiji.id where kolegiji.id = :idKolegij", nativeQuery = true)
    List<Student> findStudentByIdKolegij(@Param("idKolegij") Long idKolegij);
    @Query(value = "select * from studenti where to_tsvector(ime || ' ' || prezime || ' ' || jmbag) @@ plainto_tsquery(:string)", nativeQuery = true)
    List<Student> ftsStudents(@Param("string") String string);
}
