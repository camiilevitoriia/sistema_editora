package br.ufrn.eaj.sistema_editora.dto;

import jakarta.validation.constraints.NotBlank;

public record ResumoLivroRequestDTO(
        @NotBlank(message = "Texto do resumo e obrigatorio")
        String texto
) {
}
