package com.unp.bibliotecavirtual.control;

import com.unp.bibliotecavirtual.dto.mapper.EmprestimoMapperDTO;
import com.unp.bibliotecavirtual.dto.request.EmprestimoRequestDTO;
import com.unp.bibliotecavirtual.dto.response.EmprestimoResponseDTO;
import com.unp.bibliotecavirtual.exceptions.ClienteNaoEncontrado;
import com.unp.bibliotecavirtual.exceptions.EmprestimoNotFoundException;
import com.unp.bibliotecavirtual.exceptions.LivroNaoDisponivelException;
import com.unp.bibliotecavirtual.exceptions.LivroNotFoundException;
import com.unp.bibliotecavirtual.model.Emprestimo;
import com.unp.bibliotecavirtual.service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<?> registrarEmprestimo(@RequestBody EmprestimoRequestDTO request){
        try {
            Emprestimo emprestimo = emprestimoService.registrarEmprestimo(request.livroId(), request.clienteId());
            EmprestimoResponseDTO response = EmprestimoMapperDTO.toResponse(emprestimo);
            return ResponseEntity.ok(response);
        } catch (LivroNaoDisponivelException | LivroNotFoundException | ClienteNaoEncontrado exception){
            return ResponseEntity.status(NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<?> buscarEmprestimoPorCliente(@PathVariable Long clienteId){
        try {
            List<Emprestimo> emprestimoDTO = emprestimoService.buscarEmprestimosPorCliente(clienteId);
            return ResponseEntity.ok(emprestimoDTO);
        }catch (ClienteNaoEncontrado exception){
            return ResponseEntity.status(NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> listarTodos(){
        return ResponseEntity.ok(emprestimoService.listarTodos());
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<?> registrarDevolucao(@PathVariable Long id, @RequestBody LocalDate dataDevolucaoReal){
        try {
            Emprestimo emprestimo = emprestimoService.registrarDevolucao(id, dataDevolucaoReal);
            return ResponseEntity.ok(emprestimo);
        }catch (LivroNotFoundException | EmprestimoNotFoundException exception){
            return ResponseEntity.status(NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEmprestimo(@PathVariable Long id){
        try {
            emprestimoService.deletarEmprestimo(id);
            return ResponseEntity.noContent().build();
        }catch (EmprestimoNotFoundException exception){
            return ResponseEntity.status(NOT_FOUND).body(exception.getMessage());
        }
    }
}
