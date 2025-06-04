package com.unp.bibliotecavirtual.dto.response;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String email,
        String senha
) {
}