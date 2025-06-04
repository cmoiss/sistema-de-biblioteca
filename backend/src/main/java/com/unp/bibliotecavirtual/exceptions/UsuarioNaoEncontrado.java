package com.unp.bibliotecavirtual.exceptions;

public class UsuarioNaoEncontrado extends RuntimeException{
    public UsuarioNaoEncontrado() {
        super("Ocorreu uma exceção: Usuário não encontrado"); 
    }

    public UsuarioNaoEncontrado(String mensagem){
        super(mensagem);
    }
}