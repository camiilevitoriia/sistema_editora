package br.ufrn.eaj.sistema_editora.errorhandling;

public class CredenciaisInvalidasException extends RuntimeException {
    public CredenciaisInvalidasException(String message) {
        super(message);
    }
}
