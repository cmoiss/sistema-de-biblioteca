package com.unp.bibliotecavirtual.service;

import com.unp.bibliotecavirtual.modelo.Emprestimo;
import com.unp.bibliotecavirtual.modelo.Livro;
import com.unp.bibliotecavirtual.repositorio.EmprestimoRepository;
import com.unp.bibliotecavirtual.repositorio.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public Emprestimo registrarEmprestimo(Long livroId, Long clienteId) {
        Optional<Livro> livroOptional = livroRepository.findById(livroId);
        if (livroOptional.isEmpty()) {
            throw new RuntimeException("Livro não encontrado");
        }

        Livro livro = livroOptional.get();
        if (livro.getQuantidadeDisponivel() <= 0) {
            throw new RuntimeException("Livro não disponível para empréstimo");
        }

        int prazo = calcularPrazo(livro);
        LocalDate dataDevolucao = LocalDate.now().plusDays(prazo);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(dataDevolucao);


        emprestimoRepository.save(emprestimo);

        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
        livroRepository.save(livro);

        return emprestimo;
    }

    private int calcularPrazo(Livro livro) {
        int numeroPaginas = livro.getPaginas();
        return (numeroPaginas / 100) + 7;
    }

    public Emprestimo registrarDevolucao(Long emprestimoId, LocalDate dataDevolucaoReal) {
        Optional<Emprestimo> emprestimoOptional = emprestimoRepository.findById(emprestimoId);
        if (emprestimoOptional.isEmpty()) {
            throw new RuntimeException("Empréstimo não encontrado");
        }

        Emprestimo emprestimo = emprestimoOptional.get();
        LocalDate dataPrevista = emprestimo.getDataDevolucao();

        if (dataDevolucaoReal.isAfter(dataPrevista)) {
            long diasAtraso = ChronoUnit.DAYS.between(dataPrevista, dataDevolucaoReal);
            double valorMulta = diasAtraso * emprestimo.getMulta().getValorPorDia();
            emprestimo.getMulta().setValorCalculado(valorMulta);
        } else {
            emprestimo.getMulta().setValorCalculado(0.0);
        }


        Livro livro = emprestimo.getLivro();
        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);
        livroRepository.save(livro);

        emprestimoRepository.save(emprestimo);

        return emprestimo;
    }

    public List<Emprestimo> listarEmprestimosAtivosPorCliente(Long clienteId) {
        return emprestimoRepository.findByClienteIdAndDataDevolucaoRealIsNull(clienteId);
    }
}
