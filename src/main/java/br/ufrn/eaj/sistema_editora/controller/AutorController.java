package br.ufrn.eaj.sistema_editora.controller;

import br.ufrn.eaj.sistema_editora.domain.Autor;
import br.ufrn.eaj.sistema_editora.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorController {
    @Autowired
    private AutorService service;

    @GetMapping
    public List<Autor> listar() { return service.buscarTodos(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autor inserir(@RequestBody Autor autor) { return service.salvar(autor); }
}