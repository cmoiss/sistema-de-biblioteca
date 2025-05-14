package com.unp.bibliotecavirtual.model;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
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

    public Livro(String titulo, String autor, String isbn, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.genero = genero;
    }

    public Livro(String titulo, String autor, String genero, String isbn, String sinopse, Integer quantidadeTotal) {
        this(titulo, autor, genero, isbn);
        this.sinopse = sinopse;
        this.quantidadeTotal = quantidadeTotal;
    }
}
