package br.ufrn.eaj.sistema_editora.service;

import br.ufrn.eaj.sistema_editora.domain.Editora;
import br.ufrn.eaj.sistema_editora.domain.Livro;
import br.ufrn.eaj.sistema_editora.domain.ResumoLivro;
import br.ufrn.eaj.sistema_editora.dto.LivroRequestDTO;
import br.ufrn.eaj.sistema_editora.dto.LivroResponseDTO;
import br.ufrn.eaj.sistema_editora.repository.EditoraRepository;
import br.ufrn.eaj.sistema_editora.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Transactional(readOnly = true)
    public List<LivroResponseDTO> buscarTodos() {
        return repository.findAll().stream()
                .map(LivroResponseDTO::fromEntity)
                .toList();
    }

    @Transactional
    public LivroResponseDTO salvar(LivroRequestDTO dto) {
        Editora editora = editoraRepository.findById(dto.editoraId())
                .orElseThrow(() -> new EntityNotFoundException("Editora nao encontrada"));

        Livro livro = new Livro();
        livro.setTitulo(dto.titulo());
        livro.setIsbn(dto.isbn());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setEditora(editora);

        ResumoLivro resumo = new ResumoLivro();
        resumo.setTexto(dto.resumoTexto());
        resumo.setLivro(livro);
        livro.setResumo(resumo);

        return LivroResponseDTO.fromEntity(repository.save(livro));
    }
}
