package com.unp.biblioteca.virtual.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
public class Livro {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Setter
    @Column(unique = true)
    private String titulo;

    @Setter
    private String autor;

    @Setter
    private String genero;

    @Setter
    @Column(unique = true)
    private String isbn;

    @Setter
    private String sinopse;

    @Setter
    private Integer quantidadeTotal;
}
