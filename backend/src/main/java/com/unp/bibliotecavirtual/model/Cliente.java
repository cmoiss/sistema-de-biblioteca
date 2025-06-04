package com.unp.bibliotecavirtual.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Setter
    @Column(nullable = false)
    private String nome;

    @Column(unique = true)
    private String cpf;

    @NonNull
    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @NonNull
    @Setter
    @Column(nullable = false)
    private String senha;


    public void avaliarLivro(Livro livro, Integer like) {
        // lógica de avaliação
    }
}
