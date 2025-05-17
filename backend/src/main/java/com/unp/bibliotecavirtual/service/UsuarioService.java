package com.unp.bibliotecavirtual.service;

import com.unp.bibliotecavirtual.model.Usuario;
import com.unp.bibliotecavirtual.repository.UsuarioRepository;
import com.unp.bibliotecavirtual.service.strategy.usuario.UsuarioValidationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.unp.bibliotecavirtual.service.UsuarioValidator.validarIdNulo;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<UsuarioValidationStrategy> validacoes;

    public Usuario cadastrar(Usuario usuario) {
        validacoes.forEach(validacao -> validacao.validar(usuario));
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        // Adicione validação de id se desejar
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario editar(Usuario usuario) {
        validarIdNulo(usuario);
        Optional<Usuario> existente = usuarioRepository.findById(usuario.getId());
        if (existente.isPresent()) {
            validacoes.forEach(validacao -> validacao.validar(usuario));
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado para edição");
        }
    }

    public void deletar(Usuario usuario) {
        validarIdNulo(usuario);
        Optional<Usuario> existente = usuarioRepository.findById(usuario.getId());
        if (existente.isPresent()) {
            usuarioRepository.delete(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado para exclusão");
        }
    }
}
