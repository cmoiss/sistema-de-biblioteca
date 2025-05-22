package com.unp.bibliotecavirtual.service;

import com.unp.bibliotecavirtual.model.Usuario;
import com.unp.bibliotecavirtual.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrar(Usuario usuario) {
        // Adicione validações básicas se necessário
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario editar(Usuario usuario) {
        if (usuario.getId() == null) {
            throw new IllegalArgumentException("ID do usuário não pode ser nulo para edição");
        }
        if (usuarioRepository.existsById(usuario.getId())) {
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado para edição");
        }
    }

    public Usuario editar(Long id, Usuario usuarioAtualizado) {
        Optional<Usuario> existente = usuarioRepository.findById(id);

        if (existente.isEmpty()) throw new EntityNotFoundException("Usuário não encontrado");

        Usuario storedUser = existente.get();

        storedUser.setNome(usuarioAtualizado.getNome());
        storedUser.setLogin(usuarioAtualizado.getLogin());
        storedUser.setSenha(usuarioAtualizado.getSenha());
        // Adicione outros campos que desejar atualizar

        return usuarioRepository.save(storedUser);
    }

    public void deletar(Usuario usuario) {
        if (usuario.getId() == null) {
            throw new IllegalArgumentException("ID do usuário não pode ser nulo para exclusão");
        }
        if (usuarioRepository.existsById(usuario.getId())) {
            usuarioRepository.delete(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado para exclusão");
        }
    }
}
