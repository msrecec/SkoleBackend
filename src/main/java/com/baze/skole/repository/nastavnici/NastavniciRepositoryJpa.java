package com.baze.skole.repository.nastavnici;

import com.baze.skole.model.nastavnici.Nastavnik;
import com.baze.skole.model.studenti.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface NastavniciRepositoryJpa extends JpaRepository<Nastavnik, Long> {
    @Query(value = "select * from nastavnici inner join izvrsitelji on nastavnici.id = izvrsitelji.id_nastavnik inner join kolegiji on izvrsitelji.id_kolegij = kolegiji.id where kolegiji.id = :idKolegij", nativeQuery = true)
    List<Nastavnik> findNastavnikByIdKolegij(@Param("idKolegij") Long idKolegij);
    @Query(value = "select * from nastavnici where to_tsvector(ime || ' ' || prezime || ' ' || jmbg) @@ plainto_tsquery(?1)",
            countQuery = "select count(*) from nastavnici where to_tsvector(ime || ' ' || prezime || ' ' || jmbg) @@ plainto_tsquery(?1)",
            nativeQuery = true)
    Page<Nastavnik> ftsNastavnici(String string, Pageable pageable);
}
