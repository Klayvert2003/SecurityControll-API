package com.github.klayvert.securitycontroll.domain.service;

import com.github.klayvert.securitycontroll.domain.entity.Grupo;
import com.github.klayvert.securitycontroll.domain.entity.Usuario;
import com.github.klayvert.securitycontroll.domain.entity.UsuarioGrupo;
import com.github.klayvert.securitycontroll.domain.repository.GrupoRepository;
import com.github.klayvert.securitycontroll.domain.repository.UsuarioGrupoRepository;
import com.github.klayvert.securitycontroll.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final GrupoRepository grupoRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioGrupoRepository usuarioGrupoRepository;

    public Usuario salvar(Usuario usuario, List<String> grupos) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);

        List<UsuarioGrupo> listaUsuarioGrupo = grupos.stream()
                .map(nomeGrupo -> {
                    Optional<Grupo> possivelGrupo = grupoRepository.findByNome(nomeGrupo);
                    if (possivelGrupo.isPresent()) {
                        Grupo grupo = possivelGrupo.get();
                        return new UsuarioGrupo(usuario, grupo);
                    }
                    return null;
                }).filter(Objects::nonNull).toList();

        usuarioGrupoRepository.saveAll(listaUsuarioGrupo);

        return usuario;
    }

    public Usuario obterUsuarioComPermissoes(String login) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(login);
        if (usuarioOptional.isEmpty()) {
            return null;
        }

        Usuario usuario = usuarioOptional.get();
        List<String> permissoes = usuarioGrupoRepository.findPermissoesByUsuario(usuario);
        usuario.setPermissoes(permissoes);

        return usuario;
    }
}
