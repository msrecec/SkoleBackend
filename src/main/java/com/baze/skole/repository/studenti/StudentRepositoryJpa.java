package com.baze.skole.repository.studenti;

import com.baze.skole.model.mjesta.Mjesto;
import com.baze.skole.model.studenti.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepositoryJpa extends JpaRepository<Student, Long> {
    @Query(value = "select * from studenti inner join ocjene on studenti.id = ocjene.id_student inner join kolegiji on ocjene.id_kolegij = kolegiji.id where kolegiji.id = :idKolegij", nativeQuery = true)
    List<Student> findStudentByIdKolegij(@Param("idKolegij") Long idKolegij);
    @Query(value = "select * from studenti where to_tsvector(ime || ' ' || prezime || ' ' || jmbag) @@ plainto_tsquery(:string)",
            countQuery = "select count(*) from studenti where to_tsvector(ime || ' ' || prezime || ' ' || jmbag) @@ plainto_tsquery(:string)",
            nativeQuery = true)
    Page<Student> ftsStudents(@Param("string") String string, Pageable page);
    @Query(value = "select st.* from studenti st inner join smjerovi sm on st.id_smjer = sm.id where st.id_smjer = :idSmjer and st.id not in (select id_student from ocjene inner join kolegiji on kolegiji.id = ocjene.id_kolegij where kolegiji.id = :idKolegij)",
            countQuery = "select count(*) from studenti st inner join smjerovi sm on st.id_smjer = sm.id where st.id_smjer = :idSmjer and st.id not in (select id_student from ocjene inner join kolegiji on kolegiji.id = ocjene.id_kolegij where kolegiji.id = :idKolegij)",
            nativeQuery = true)
    Page<Student> findStudentByIdSmjerPaginated(@Param("idSmjer") Long idSmjer, @Param("idKolegij") Long idKolegij, Pageable page);
}
