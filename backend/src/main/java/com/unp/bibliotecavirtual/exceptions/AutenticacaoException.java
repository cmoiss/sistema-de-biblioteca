package com.unp.bibliotecavirtual.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED) // Retorna o código 401 Unauthorized
public class AutenticacaoException extends RuntimeException {
    public AutenticacaoException(String message) {
        super(message);
    }
}