package br.ufrn.eaj.sistema_editora.errorhandling;

public class RegraNegocioException extends RuntimeException {
    public RegraNegocioException(String message) {
        super(message);
    }
}
