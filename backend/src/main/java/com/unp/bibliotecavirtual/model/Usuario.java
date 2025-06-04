package com.unp.bibliotecavirtual.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@MappedSuperclass
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Setter
    @Column(nullable = false)
    private String nome;

    @NonNull
    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @NonNull
    @Setter
    @Column(nullable = false)
    private String senha;

    public abstract void solicitarEmprestimo(Usuario usuario, Livro livro);

    public abstract void devolverLivro(Usuario usuario, Livro livro);
}
