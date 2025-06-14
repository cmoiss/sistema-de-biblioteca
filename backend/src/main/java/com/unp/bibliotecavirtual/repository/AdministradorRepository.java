package com.unp.bibliotecavirtual.repository;

import com.unp.bibliotecavirtual.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    // Aqui entrariam métodos de busca específicos para Administradores,
    // por exemplo: findByNivelPermissao(Permissao nivel);
}