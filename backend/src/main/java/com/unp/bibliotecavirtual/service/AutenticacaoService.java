package com.unp.bibliotecavirtual.service;

// Imports atualizados para os pacotes corretos
import com.unp.bibliotecavirtual.dto.mapper.UsuarioMapper;
import com.unp.bibliotecavirtual.dto.request.LoginRequestDTO;
import com.unp.bibliotecavirtual.dto.response.LoginResponseDTO;

import com.unp.bibliotecavirtual.exceptions.AutenticacaoException;
import com.unp.bibliotecavirtual.model.Usuario;
import com.unp.bibliotecavirtual.model.Cliente;
import com.unp.bibliotecavirtual.model.Administrador;
import com.unp.bibliotecavirtual.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutenticacaoService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AutenticacaoService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public UsuarioTipoResponse autenticar(LoginRequestDTO loginRequestDTO) {
        Usuario usuario = usuarioRepository.findByEmail(loginRequestDTO.email())
                .orElseThrow(() -> new AutenticacaoException("Credenciais inválidas."));

        if (!passwordEncoder.matches(loginRequestDTO.senha(), usuario.getSenha())) {
            throw new AutenticacaoException("Credenciais inválidas.");
        }

        boolean isCliente = usuario instanceof Cliente;
        boolean isAdministrador = usuario instanceof Administrador;

        return new UsuarioTipoResponse(isCliente, isAdministrador);
    }

    public static class UsuarioTipoResponse {
        public final boolean isCliente;
        public final boolean isAdministrador;

        public UsuarioTipoResponse(boolean isCliente, boolean isAdministrador) {
            this.isCliente = isCliente;
            this.isAdministrador = isAdministrador;
        }
    }
}