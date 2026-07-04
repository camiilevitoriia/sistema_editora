package br.ufrn.eaj.sistema_editora.controller;

import br.ufrn.eaj.sistema_editora.domain.ResumoLivro;
import br.ufrn.eaj.sistema_editora.service.ResumoLivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resumos")
public class ResumoLivroController {
    @Autowired
    private ResumoLivroService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResumoLivro inserir(@RequestBody ResumoLivro resumo) { return service.salvar(resumo); }
}