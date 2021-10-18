package com.baze.skole.repository.kolegiji;

import com.baze.skole.model.kolegiji.Kolegij;
import com.baze.skole.model.smjerovi.Smjer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KolegijRepositoryJpa extends JpaRepository<Kolegij, Long> {
    @Query(value = "select * from kolegiji inner join smjerovi on kolegiji.id_smjer = smjerovi.id where smjerovi.id = :idSmjer", nativeQuery = true)
    List<Kolegij> findKolegijiByIdSmjera(@Param("idSmjer") Long idSmjer);
}
