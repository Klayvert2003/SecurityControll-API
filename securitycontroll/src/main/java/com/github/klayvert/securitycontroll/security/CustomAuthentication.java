package com.github.klayvert.securitycontroll.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

public class CustomAuthentication implements Authentication {

    private final IdentificacaoUsuario identificacaoUsuario;

    public CustomAuthentication(IdentificacaoUsuario identificacaoUsuario) {
        if (identificacaoUsuario == null) {
            throw new ExceptionInInitializerError("Não é possível criar um custom authentication sem a identificação do usuário");
        }

        this.identificacaoUsuario = identificacaoUsuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.identificacaoUsuario
                .getPermissoes()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.identificacaoUsuario;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("Não precisa chamar, já estamos autenticados");
    }

    @Override
    public String getName() {
        return this.identificacaoUsuario.getNome();
    }
}
