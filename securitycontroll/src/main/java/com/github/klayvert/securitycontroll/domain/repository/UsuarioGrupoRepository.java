package com.github.klayvert.securitycontroll.domain.repository;

import com.github.klayvert.securitycontroll.domain.entity.Usuario;
import com.github.klayvert.securitycontroll.domain.entity.UsuarioGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioGrupoRepository extends JpaRepository<UsuarioGrupo, String> {

    @Query("""
            SELECT
                DISTINCT g.nome
            FROM
                UsuarioGrupo ug
            JOIN
                ug.grupo g
            JOIN
                ug.usuario u
            WHERE
                u = ?1
    """)
    List<String> findPermissoesByUsuario(Usuario usuario);
}
