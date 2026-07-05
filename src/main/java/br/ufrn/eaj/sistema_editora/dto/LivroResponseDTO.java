package br.ufrn.eaj.sistema_editora.dto;

import br.ufrn.eaj.sistema_editora.domain.Livro;

import java.util.List;

public record LivroResponseDTO(
        Long id,
        String titulo,
        String isbn,
        Integer anoPublicacao,
        Long editoraId,
        String editoraNome,
        Long resumoId,
        String resumoTexto,
        List<AutorResumoDTO> autores
) {
    public static LivroResponseDTO fromEntity(Livro livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getAnoPublicacao(),
                livro.getEditora() != null ? livro.getEditora().getId() : null,
                livro.getEditora() != null ? livro.getEditora().getNome() : null,
                livro.getResumo() != null ? livro.getResumo().getId() : null,
                livro.getResumo() != null ? livro.getResumo().getTexto() : null,
                livro.getAutores().stream()
                        .map(AutorResumoDTO::fromEntity)
                        .toList()
        );
    }
}
