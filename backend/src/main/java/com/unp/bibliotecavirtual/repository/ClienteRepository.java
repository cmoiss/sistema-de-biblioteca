package com.unp.bibliotecavirtual.repository;

import java.util.List;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unp.bibliotecavirtual.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByEmail(String email);

    boolean existsByCpf(String cpf);

    List<Cliente> findByDeletedFalse();
}
