package com.unp.bibliotecavirtual.dto.mapper;

import com.unp.bibliotecavirtual.dto.request.EmprestimoRequestDTO;
import com.unp.bibliotecavirtual.dto.response.EmprestimoResponseDTO;
import com.unp.bibliotecavirtual.model.Emprestimo;

public class EmprestimoMapperDTO {
    public static Emprestimo toEntity(EmprestimoRequestDTO request){
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataEmprestimo(request.dataEmprestimo());
        emprestimo.setDataDevolucao(request.dataDevolucao());
        emprestimo.setAtivo(true);
        return emprestimo;
    }

    public static EmprestimoResponseDTO toResponse(Emprestimo emprestimo){
        return new EmprestimoResponseDTO(
                emprestimo.getId(),
                emprestimo.getCliente().getId(),
                emprestimo.getCliente().getNome(),
                emprestimo.getCliente().getCpf(),
                emprestimo.getLivro().getIsbn(),
                emprestimo.getLivro().getId(),
                emprestimo.getLivro().getTitulo(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao(),
                emprestimo.getMulta() != null ? emprestimo.getMulta().getValorCalculado() : null,
                emprestimo.isAtivo()

        );
    }
}
