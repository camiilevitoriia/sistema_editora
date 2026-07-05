package br.ufrn.eaj.sistema_editora.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record LivroRequestDTO(
        @NotBlank(message = "Titulo e obrigatorio")
        String titulo,

        @NotBlank(message = "ISBN e obrigatorio")
        @Pattern(
                regexp = "^(?:\\d{9}[\\dXx]|\\d{13}|\\d(?:[- ]?\\d){8}[- ]?[\\dXx]|\\d(?:[- ]?\\d){12})$",
                message = "ISBN deve estar em formato valido"
        )
        String isbn,

        @NotNull(message = "Ano de publicacao e obrigatorio")
        @Positive(message = "Ano de publicacao deve ser positivo")
        Integer anoPublicacao,

        @NotNull(message = "Editora e obrigatoria")
        Long editoraId,

        @NotBlank(message = "Resumo e obrigatorio")
        String resumoTexto
) {
}
