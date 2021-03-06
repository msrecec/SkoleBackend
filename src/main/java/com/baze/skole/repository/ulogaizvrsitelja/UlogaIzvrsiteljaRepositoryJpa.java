package com.baze.skole.repository.ulogaizvrsitelja;

import com.baze.skole.model.studenti.Student;
import com.baze.skole.model.ulogaizvrsitelja.UlogaIzvrsitelja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UlogaIzvrsiteljaRepositoryJpa extends JpaRepository<UlogaIzvrsitelja, Long> {
    @Query(value = "select * from uloga_izvrsitelja inner join izvrsitelji on uloga_izvrsitelja.id = izvrsitelji.id_uloga_izvrsitelja  where izvrsitelji.id_nastavnik = :idNastavnik and izvrsitelji.id_kolegij = :idKolegij", nativeQuery = true)
    List<UlogaIzvrsitelja> findUlogaIzvrsiteljaByNastavnikId(@Param("idNastavnik") Long idNastavnik, @Param("idKolegij") Long idKolegij);
}
