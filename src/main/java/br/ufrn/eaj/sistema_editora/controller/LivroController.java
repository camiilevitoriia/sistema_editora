package br.ufrn.eaj.sistema_editora.controller;

import br.ufrn.eaj.sistema_editora.dto.LivroRequestDTO;
import br.ufrn.eaj.sistema_editora.dto.LivroResponseDTO;
import br.ufrn.eaj.sistema_editora.service.LivroService;
import jakarta.validation.Valid;
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
    public List<LivroResponseDTO> listar() { return service.buscarTodos(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroResponseDTO inserir(@Valid @RequestBody LivroRequestDTO livro) { return service.salvar(livro); }
}
