package com.baze.skole.repository.zupanije;

import com.baze.skole.model.zupanije.Zupanija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZupanijeRepositoryJpa extends JpaRepository<Zupanija,Long> {
}
