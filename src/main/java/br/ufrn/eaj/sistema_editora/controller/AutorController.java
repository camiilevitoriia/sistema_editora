package br.ufrn.eaj.sistema_editora.controller;

import br.ufrn.eaj.sistema_editora.dto.AutorRequestDTO;
import br.ufrn.eaj.sistema_editora.dto.AutorResponseDTO;
import br.ufrn.eaj.sistema_editora.service.AutorService;
import jakarta.validation.Valid;
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
    public List<AutorResponseDTO> listar() { return service.buscarTodos(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutorResponseDTO inserir(@Valid @RequestBody AutorRequestDTO autor) { return service.salvar(autor); }
}
