package br.ufrn.eaj.sistema_editora.dto;

import br.ufrn.eaj.sistema_editora.domain.Autor;

public record AutorResumoDTO(
        Long id,
        String nome
) {
    public static AutorResumoDTO fromEntity(Autor autor) {
        return new AutorResumoDTO(autor.getId(), autor.getNome());
    }
}
