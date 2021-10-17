package com.baze.skole.repository.nastavnici;

import com.baze.skole.model.nastavnici.Nastavnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NastavniciRepositoryJpa extends JpaRepository<Nastavnik, Long> {
}
