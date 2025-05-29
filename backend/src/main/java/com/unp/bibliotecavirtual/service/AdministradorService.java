package com.unp.bibliotecavirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unp.bibliotecavirtual.model.Administrador;
import com.unp.bibliotecavirtual.model.Emprestimo;
import com.unp.bibliotecavirtual.repository.AdministradorRepository;
import com.unp.bibliotecavirtual.repository.EmprestimoRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public Emprestimo solicitarEmprestimoComoAdm(Long idCliente, Long idLivro) {
        // Aqui você pode delegar para o EmprestimoService se quiser
        throw new UnsupportedOperationException("Implementar integração com EmprestimoService");
    }

    public List<Emprestimo> gerarRelatorioEmprestimos() {
        return emprestimoRepository.findAll();
    }
}
