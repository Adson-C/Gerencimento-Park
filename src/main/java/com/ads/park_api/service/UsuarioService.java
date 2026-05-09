package com.ads.park_api.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ads.park_api.entity.Usuario;
import com.ads.park_api.exception.EntityNotFoundException;
import com.ads.park_api.exception.PasswordInvalidException;
import com.ads.park_api.exception.UserNameUniqueViolationException;
import com.ads.park_api.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        try {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);

        } catch (DataIntegrityViolationException ex) {
            throw new UserNameUniqueViolationException(String.format("UserName {%s} já existe", usuario.getUsername()));
        }
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {

        return usuarioRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(String.format("Usuário com id=%s não encontrado", id))
        );

    }

    @Transactional
	public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {

		Usuario user = buscarPorId(id);

		if (!passwordEncoder.matches(senhaAtual, user.getPassword())) {
			throw new PasswordInvalidException(String.format("Senha atual não confere"));
		}
		if (!novaSenha.equals(confirmaSenha)) {
			throw new PasswordInvalidException(String.format("As senhas não coincidem"));
		}
		user.setPassword(passwordEncoder.encode(novaSenha));
		return user;
	}

    @Transactional(readOnly = true)
	public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
	}


    @Transactional(readOnly = true)
    public Usuario buscarPorUserName(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Usuário com username=%s não encontrado", username)));
    }
    @Transactional(readOnly = true)
    public Usuario.Role buscarRolePorUserName(String username) {
        return usuarioRepository.findRoleByUsername(username);
    }

}
