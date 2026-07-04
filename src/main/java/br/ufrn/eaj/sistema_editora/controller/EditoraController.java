package br.ufrn.eaj.sistema_editora.controller;

import br.ufrn.eaj.sistema_editora.domain.Editora;
import br.ufrn.eaj.sistema_editora.service.EditoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/editoras")
public class EditoraController {
    @Autowired
    private EditoraService service;

    @GetMapping
    public List<Editora> listar() { return service.buscarTodos(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Editora inserir(@RequestBody Editora editora) { return service.salvar(editora); }
}