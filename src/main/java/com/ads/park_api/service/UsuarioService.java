package com.ads.park_api.service;

import com.ads.park_api.entity.Usuario;
import com.ads.park_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {

        return usuarioRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Usuário não encontrado com id: " + id)
        );

    }

    @Transactional
	public Usuario editarSenha(Long id, String newPassword) {
		Usuario user = buscarPorId(id);
		user.setPassword(newPassword);
		return user;
	}

}
