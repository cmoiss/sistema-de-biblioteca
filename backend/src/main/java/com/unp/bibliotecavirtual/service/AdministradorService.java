package com.unp.bibliotecavirtual.service;

import com.unp.bibliotecavirtual.exceptions.RecursoNaoEncontradoException;
import com.unp.bibliotecavirtual.exceptions.RegraDeNegocioException;
import com.unp.bibliotecavirtual.model.Administrador;
import com.unp.bibliotecavirtual.repository.AdministradorRepository;
import com.unp.bibliotecavirtual.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdministradorService {

    private final AdministradorRepository adminRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AdministradorService(AdministradorRepository adminRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Administrador criarAdmin(Administrador admin) {
        if (usuarioRepository.findByEmail(admin.getEmail()).isPresent()) {
            throw new RegraDeNegocioException("E-mail já cadastrado no sistema.");
        }
        
        admin.setSenha(passwordEncoder.encode(admin.getSenha()));
        admin.setAtivo(true);

        return adminRepository.save(admin);
    }

    @Transactional(readOnly = true)
    public Administrador buscarAdminPorId(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Administrador não encontrado com o ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<Administrador> listarTodosAdmins() {
        return adminRepository.findAll();
    }

    @Transactional
    public void deletarAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Administrador não encontrado com o ID: " + id);
        }
        adminRepository.deleteById(id);
    }

    // Métodos de atualização podem ser adicionados de forma similar ao ClienteService
}