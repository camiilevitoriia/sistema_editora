package br.ufrn.eaj.sistema_editora.controller;

import br.ufrn.eaj.sistema_editora.dto.EditoraRequestDTO;
import br.ufrn.eaj.sistema_editora.dto.EditoraResponseDTO;
import br.ufrn.eaj.sistema_editora.service.EditoraService;
import jakarta.validation.Valid;
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
    public List<EditoraResponseDTO> listar() { return service.buscarTodos(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EditoraResponseDTO inserir(@Valid @RequestBody EditoraRequestDTO editora) { return service.salvar(editora); }
}
