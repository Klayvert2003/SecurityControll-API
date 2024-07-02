package com.github.klayvert.securitycontroll.config;

import com.github.klayvert.securitycontroll.security.CustomAuthentication;
import com.github.klayvert.securitycontroll.security.IdentificacaoUsuario;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SenhaMasterAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var login = authentication.getName();
        var password = authentication.getCredentials();

        String loginMaster = "master";
        String masterPassword = "@321";

        if (loginMaster.equals(login) && masterPassword.equals(password)) {
            IdentificacaoUsuario identificacaoUsuario = new IdentificacaoUsuario(
                    "Sou Master",
                    "Master",
                    loginMaster,
                    List.of("ADMIN"));

            return new CustomAuthentication(identificacaoUsuario);
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
