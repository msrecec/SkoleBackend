package com.baze.skole.repository.mjesta;

import com.baze.skole.model.mjesta.Mjesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MjestaRepositoryJpa extends JpaRepository<Mjesto,Long> {

    @Query(value = "select * from mjesta where postbr = :postbr", nativeQuery = true)
    List<Mjesto> findMjestoByPostbr(@Param("postbr") Integer postbr);
}
