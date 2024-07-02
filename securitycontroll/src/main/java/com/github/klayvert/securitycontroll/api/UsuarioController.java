package com.github.klayvert.securitycontroll.api;

import com.github.klayvert.securitycontroll.api.dto.CadastroUsuarioDTO;
import com.github.klayvert.securitycontroll.domain.entity.Usuario;
import com.github.klayvert.securitycontroll.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> salvar(@RequestBody CadastroUsuarioDTO dto) {
        Usuario usuario = service.salvar(dto.getUsuario(), dto.getPermissoes());
        return ResponseEntity.ok(usuario);
    }
}
