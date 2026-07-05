package br.ufrn.eaj.sistema_editora.dto;

import br.ufrn.eaj.sistema_editora.domain.Autor;

public record AutorResponseDTO(
        Long id,
        String nome,
        String nacionalidade
) {
    public static AutorResponseDTO fromEntity(Autor autor) {
        return new AutorResponseDTO(
                autor.getId(),
                autor.getNome(),
                autor.getNacionalidade()
        );
    }
}
