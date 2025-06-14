package com.unp.bibliotecavirtual.repository;

import com.unp.bibliotecavirtual.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByDeletedFalse();
}
