package com.unp.biblioteca.virtual.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Livros")
public class BibliotecaVirtualController {

    @Autowired //injecao de dependencias
    private final LivroService livroService;

}
