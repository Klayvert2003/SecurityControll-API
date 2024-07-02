package com.github.klayvert.securitycontroll.api.dto;

import com.github.klayvert.securitycontroll.domain.entity.Usuario;
import lombok.Data;

import java.util.List;

@Data
public class CadastroUsuarioDTO {
    private Usuario usuario;
    private List<String> permissoes;
}
