package br.ufrn.eaj.sistema_editora.dto;

import br.ufrn.eaj.sistema_editora.domain.Editora;

public record EditoraResponseDTO(
        Long id,
        String nome,
        String cnpj
) {
    public static EditoraResponseDTO fromEntity(Editora editora) {
        return new EditoraResponseDTO(
                editora.getId(),
                editora.getNome(),
                editora.getCnpj()
        );
    }
}
