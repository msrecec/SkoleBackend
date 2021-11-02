package com.baze.skole.repository.izvrsitelji;

import com.baze.skole.model.izvrsitelji.Izvrsitelj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IzvrsiteljRepositoryJpa extends JpaRepository<Izvrsitelj, Long> {
    @Query(value = "select * from izvrsitelji where izvrsitelji.id_nastavnik = :idNastavnik and izvrsitelji.id_kolegij = :idKolegij", nativeQuery = true)
    List<Izvrsitelj> findIzvrsiteljByIdNastavnikAndIdKolegij(@Param("idNastavnik") Long idNastavnik, @Param("idKolegij") Long idKolegij);
}
