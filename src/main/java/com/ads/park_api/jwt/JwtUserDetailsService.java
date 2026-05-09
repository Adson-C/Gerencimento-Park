package com.ads.park_api.jwt;

import com.ads.park_api.entity.Usuario;
import com.ads.park_api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Override
    public JwtUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.buscarPorUserName(username);
        return new JwtUserDetails(usuarioService.buscarPorUserName(username));
    }
    public JwtToken getTokenAthentication(String username) {
        Usuario.Role role = usuarioService.buscarRolePorUserName(username);
        return JwtUtils.createToken(username, role.name().substring("ROLE_".length()));
    }
}
