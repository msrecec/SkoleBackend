package com.baze.skole.repository.mjesta;

import com.baze.skole.model.mjesta.Mjesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MjestaRepositoryJpa extends JpaRepository<Mjesto,Long> {
}
