package com.baze.skole.repository.ustanove;

import com.baze.skole.model.ustanove.Ustanova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UstanoveRepositoryJpa extends JpaRepository<Ustanova, Long> {
}
