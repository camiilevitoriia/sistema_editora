package br.ufrn.eaj.sistema_editora.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
        @NotBlank(message = "Usuario e obrigatorio")
        String usuario,

        @NotBlank(message = "Senha e obrigatoria")
        String senha
) {
}
