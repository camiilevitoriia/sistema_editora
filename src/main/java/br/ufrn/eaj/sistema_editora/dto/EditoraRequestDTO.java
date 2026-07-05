package br.ufrn.eaj.sistema_editora.dto;

import jakarta.validation.constraints.NotBlank;

public record EditoraRequestDTO(
        @NotBlank(message = "Nome da editora e obrigatorio")
        String nome,

        @NotBlank(message = "CNPJ da editora e obrigatorio")
        String cnpj
) {
}
