package com.unp.bibliotecavirtual.dto.request;

import java.time.LocalDate;

public record EmprestimoRequestDTO(
        Long clienteId,
        Long livroId,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao
) {
}
