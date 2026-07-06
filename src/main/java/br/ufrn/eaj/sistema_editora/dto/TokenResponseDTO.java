package br.ufrn.eaj.sistema_editora.dto;

public record TokenResponseDTO(
        String tipo,
        String token,
        long expiraEm
) {
}
