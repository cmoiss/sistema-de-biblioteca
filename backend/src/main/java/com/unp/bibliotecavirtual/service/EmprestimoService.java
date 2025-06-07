package com.unp.bibliotecavirtual.service;

import com.unp.bibliotecavirtual.exceptions.ClienteNaoEncontrado;
import com.unp.bibliotecavirtual.exceptions.LivroNaoDisponivelException;
import com.unp.bibliotecavirtual.exceptions.LivroNotFoundException;
import com.unp.bibliotecavirtual.model.Cliente;
import com.unp.bibliotecavirtual.model.Emprestimo;
import com.unp.bibliotecavirtual.model.Livro;
import com.unp.bibliotecavirtual.repository.ClienteRepository;
import com.unp.bibliotecavirtual.repository.EmprestimoRepository;
import com.unp.bibliotecavirtual.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.unp.bibliotecavirtual.service.CalcularPrazoEmprestimo.calcularPrazo;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Emprestimo registrarEmprestimo(Long livroId, Long clienteId) throws LivroNotFoundException, LivroNaoDisponivelException, ClienteNaoEncontrado {
        Livro livro = livroRepository.findById(livroId).orElseThrow(LivroNotFoundException::new);
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(ClienteNaoEncontrado::new);

        if (livro.getExemplaresDisponiveisEmEstoque() <= 0) throw new LivroNaoDisponivelException();

        int prazo = calcularPrazo(livro);
        LocalDate dataDevolucao = LocalDate.now().plusDays(prazo);

        Emprestimo emprestimo = new Emprestimo(cliente, livro, LocalDate.now(), dataDevolucao);
        livro.setExemplaresDisponiveisEmEstoque(livro.getExemplaresDisponiveisEmEstoque() - 1);

        emprestimoRepository.save(emprestimo);
        livroRepository.save(livro);

        return emprestimo;
    }

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

//    public List<Emprestimo> buscarEmprestimosPorCliente(Long idCliente) {
//        return emprestimoRepository.findByAutorEmprestimoId(idCliente);
//    }
//
//    public void deletarEmprestimo(Long id) {
//        Optional<Emprestimo> existente = emprestimoRepository.findById(id);
//        if (existente.isPresent()) {
//            emprestimoRepository.delete(existente.get());
//        } else {
//            throw new RuntimeException("Empréstimo não encontrado para exclusão");
//        }
//    }


    //    public Emprestimo registrarDevolucao(Long emprestimoId, LocalDate dataDevolucaoReal) {
//        Optional<Emprestimo> emprestimoOptional = emprestimoRepository.findById(emprestimoId);
//        if (emprestimoOptional.isEmpty()) {
//            throw new RuntimeException("Empréstimo não encontrado");
//        }
//
//        Emprestimo emprestimo = emprestimoOptional.get();
//        LocalDate dataPrevista = emprestimo.getDataDevolucao();
//
//        double multa = 0;
//        if (dataDevolucaoReal.isAfter(dataPrevista)) {
//            long diasAtraso = java.time.temporal.ChronoUnit.DAYS.between(dataPrevista, dataDevolucaoReal);
//            multa = diasAtraso * 2.0;
//            emprestimo.setMulta(multa);
//        }
//
//
//        Livro livro = emprestimo.getLivro();
//        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);
//        livroRepository.save(livro);
//
//        emprestimo.setDataDevolucaoReal(dataDevolucaoReal);
//        emprestimoRepository.save(emprestimo);
//
//        return emprestimo;
//    }
//    public List<Emprestimo> listarEmprestimosAtivosPorCliente(Long clienteId) {
//        return emprestimoRepository.findByClienteIdAndDataDevolucaoRealIsNull(clienteId);
//    }
}
