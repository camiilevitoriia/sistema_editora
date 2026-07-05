package br.ufrn.eaj.sistema_editora.service;

import br.ufrn.eaj.sistema_editora.domain.Editora;
import br.ufrn.eaj.sistema_editora.domain.Livro;
import br.ufrn.eaj.sistema_editora.domain.ResumoLivro;
import br.ufrn.eaj.sistema_editora.dto.LivroRequestDTO;
import br.ufrn.eaj.sistema_editora.dto.LivroResponseDTO;
import br.ufrn.eaj.sistema_editora.errorhandling.RecursoNaoEncontradoException;
import br.ufrn.eaj.sistema_editora.repository.EditoraRepository;
import br.ufrn.eaj.sistema_editora.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable)
                .map(LivroResponseDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public LivroResponseDTO buscarPorId(Long id) {
        return LivroResponseDTO.fromEntity(buscarEntidadePorId(id));
    }

    @Transactional
    public LivroResponseDTO salvar(LivroRequestDTO dto) {
        Livro livro = new Livro();
        preencherLivro(livro, dto);

        ResumoLivro resumo = new ResumoLivro();
        resumo.setTexto(dto.resumoTexto());
        resumo.setLivro(livro);
        livro.setResumo(resumo);

        return LivroResponseDTO.fromEntity(repository.save(livro));
    }

    @Transactional
    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
        Livro livro = buscarEntidadePorId(id);
        preencherLivro(livro, dto);

        if (livro.getResumo() == null) {
            ResumoLivro resumo = new ResumoLivro();
            resumo.setLivro(livro);
            livro.setResumo(resumo);
        }
        livro.getResumo().setTexto(dto.resumoTexto());

        return LivroResponseDTO.fromEntity(repository.save(livro));
    }

    @Transactional
    public void excluir(Long id) {
        Livro livro = buscarEntidadePorId(id);
        repository.delete(livro);
    }

    private Livro buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Livro nao encontrado"));
    }

    private void preencherLivro(Livro livro, LivroRequestDTO dto) {
        Editora editora = editoraRepository.findById(dto.editoraId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Editora nao encontrada"));

        livro.setTitulo(dto.titulo());
        livro.setIsbn(dto.isbn());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setEditora(editora);
    }
}
