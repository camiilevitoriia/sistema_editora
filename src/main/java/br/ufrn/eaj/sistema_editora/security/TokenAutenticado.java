package br.ufrn.eaj.sistema_editora.security;

public record TokenAutenticado(
        String usuario,
        String papel
) {
}
