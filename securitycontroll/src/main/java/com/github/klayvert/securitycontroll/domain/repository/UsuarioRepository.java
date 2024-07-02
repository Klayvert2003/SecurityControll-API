package com.github.klayvert.securitycontroll.domain.repository;

import com.github.klayvert.securitycontroll.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByLogin(String login);
}
