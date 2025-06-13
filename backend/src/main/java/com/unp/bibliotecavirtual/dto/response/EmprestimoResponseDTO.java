package com.unp.bibliotecavirtual.dto.response;

import java.time.LocalDate;

public record EmprestimoResponseDTO(
        Long id,
        Long clienteId,
        String nomeCliente,
        String cpf,
        String isbn,
        Long livroId,
        String tituloLivro,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao,
        Double valorMulta,
        boolean isAtivo
) {
}
