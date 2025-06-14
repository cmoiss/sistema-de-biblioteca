package com.unp.bibliotecavirtual.dto.mapper;

import com.unp.bibliotecavirtual.dto.response.LoginResponseDTO;
import com.unp.bibliotecavirtual.model.Administrador;
import com.unp.bibliotecavirtual.model.Cliente;
import com.unp.bibliotecavirtual.model.Usuario;

public class UsuarioMapper {

    /**
     * Converte uma entidade Usuario para um DTO de resposta de login.
     * @param usuario A entidade Usuario a ser convertida.
     * @return O DTO com os dados formatados para a resposta da API.
     */
    public static LoginResponseDTO toLoginResponseDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        String tipoUsuario = "INDEFINIDO";
        if (usuario instanceof Cliente) {
            tipoUsuario = "CLIENTE";
        } else if (usuario instanceof Administrador) {
            tipoUsuario = "ADMINISTRADOR";
        }

        return new LoginResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                tipoUsuario
        );
    }
}