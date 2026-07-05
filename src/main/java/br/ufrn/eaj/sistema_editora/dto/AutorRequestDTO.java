package br.ufrn.eaj.sistema_editora.dto;

import jakarta.validation.constraints.NotBlank;

public record AutorRequestDTO(
        @NotBlank(message = "Nome do autor e obrigatorio")
        String nome,

        @NotBlank(message = "Nacionalidade do autor e obrigatoria")
        String nacionalidade
) {
}
