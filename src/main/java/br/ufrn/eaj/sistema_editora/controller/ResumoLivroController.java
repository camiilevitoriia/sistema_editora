package br.ufrn.eaj.sistema_editora.controller;

import br.ufrn.eaj.sistema_editora.dto.ResumoLivroRequestDTO;
import br.ufrn.eaj.sistema_editora.dto.ResumoLivroResponseDTO;
import br.ufrn.eaj.sistema_editora.service.ResumoLivroService;
import jakarta.validation.Valid;
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
    public ResumoLivroResponseDTO inserir(@Valid @RequestBody ResumoLivroRequestDTO resumo) { return service.salvar(resumo); }
}
