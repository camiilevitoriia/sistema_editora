package br.ufrn.eaj.sistema_editora.controller;

import br.ufrn.eaj.sistema_editora.dto.LivroRequestDTO;
import br.ufrn.eaj.sistema_editora.dto.LivroResponseDTO;
import br.ufrn.eaj.sistema_editora.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/livros")
public class LivroController {
    @Autowired
    private LivroService service;

    @GetMapping
    public ResponseEntity<Page<LivroResponseDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.buscarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> inserir(@Valid @RequestBody LivroRequestDTO livro) {
        LivroResponseDTO response = service.salvar(livro);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody LivroRequestDTO livro) {
        return ResponseEntity.ok(service.atualizar(id, livro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{livroId}/autores/{autorId}")
    public ResponseEntity<LivroResponseDTO> associarAutor(@PathVariable Long livroId, @PathVariable Long autorId) {
        return ResponseEntity.ok(service.associarAutor(livroId, autorId));
    }
}
