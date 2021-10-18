package com.baze.skole.repository.smjerovi;

import com.baze.skole.model.smjerovi.Smjer;
import com.baze.skole.model.ustanove.Ustanova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface SmjerRepositoryJpa extends JpaRepository<Smjer, Long> {
    @Query(value = "select * from smjerovi inner join ustanove on smjerovi.id_ustanova = ustanove.id where ustanove.id = :idUstanove", nativeQuery = true)
    List<Smjer> findSmjerByIdUstanove(@Param("idUstanove") Long idUstanove);
}
