package com.github.klayvert.securitycontroll.domain.repository;

import com.github.klayvert.securitycontroll.domain.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, String> {
    Optional<Grupo> findByNome(String nome);
}
