package com.unp.bibliotecavirtual.dto.response;

// Sem alterações no código, apenas no pacote.
public record LoginResponseDTO(
    Long id,
    String nome,
    String email,
    String tipoUsuario
) {}