package br.ufrn.eaj.sistema_editora.dto;

import br.ufrn.eaj.sistema_editora.domain.ResumoLivro;

public record ResumoLivroResponseDTO(
        Long id,
        String texto
) {
    public static ResumoLivroResponseDTO fromEntity(ResumoLivro resumoLivro) {
        return new ResumoLivroResponseDTO(
                resumoLivro.getId(),
                resumoLivro.getTexto()
        );
    }
}
