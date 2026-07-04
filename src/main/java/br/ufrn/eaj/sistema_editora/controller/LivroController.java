package br.ufrn.eaj.sistema_editora.controller;

import br.ufrn.eaj.sistema_editora.domain.Livro;
import br.ufrn.eaj.sistema_editora.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {
    @Autowired
    private LivroService service;

    @GetMapping
    public List<Livro> listar() { return service.buscarTodos(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Livro inserir(@RequestBody Livro livro) { return service.salvar(livro); }
}