package com.baze.skole.repository.mjesta;

import com.baze.skole.model.mjesta.Mjesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MjestaRepositoryJpa extends JpaRepository<Mjesto,Long> {
    Optional<Mjesto> findMjestoByPostbr(String postbr);
}
