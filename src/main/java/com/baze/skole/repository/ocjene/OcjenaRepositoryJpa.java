package com.baze.skole.repository.ocjene;

import com.baze.skole.model.nastavnici.Nastavnik;
import com.baze.skole.model.ocjene.Ocjena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OcjenaRepositoryJpa extends JpaRepository<Ocjena, Long> {
    @Query(value = "select * from ocjene where ocjene.id_student = :idStudent and ocjene.id_kolegij = :idKolegij", nativeQuery = true)
    List<Ocjena> findOcjenaByIdStudent(@Param("idStudent") Long idStudent, @Param("idKolegij") Long idKolegij);
}
