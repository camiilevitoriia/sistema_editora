package br.ufrn.eaj.sistema_editora.errorhandling;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErroResponse> tratarErroValidacao(MethodArgumentNotValidException ex) {
        List<String> mensagens = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .toList();

        return criarResposta(HttpStatus.BAD_REQUEST, "Dados invalidos", mensagens);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ApiErroResponse> tratarErroValidacaoMetodo(HandlerMethodValidationException ex) {
        List<String> mensagens = ex.getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage() != null ? erro.getDefaultMessage() : "Parametro invalido")
                .toList();

        return criarResposta(HttpStatus.BAD_REQUEST, "Dados invalidos", mensagens);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErroResponse> tratarViolacaoConstraint(ConstraintViolationException ex) {
        List<String> mensagens = ex.getConstraintViolations()
                .stream()
                .map(violacao -> violacao.getPropertyPath() + ": " + violacao.getMessage())
                .toList();

        return criarResposta(HttpStatus.BAD_REQUEST, "Dados invalidos", mensagens);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErroResponse> tratarJsonInvalido() {
        return criarResposta(
                HttpStatus.BAD_REQUEST,
                "Requisicao invalida",
                List.of("Corpo da requisicao ausente ou em formato invalido")
        );
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ApiErroResponse> tratarRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        return criarResposta(HttpStatus.NOT_FOUND, "Recurso nao encontrado", List.of(ex.getMessage()));
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ApiErroResponse> tratarRegraNegocio(RegraNegocioException ex) {
        return criarResposta(HttpStatus.BAD_REQUEST, "Regra de negocio violada", List.of(ex.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErroResponse> tratarIntegridadeDados() {
        return criarResposta(
                HttpStatus.BAD_REQUEST,
                "Regra de negocio violada",
                List.of("Operacao viola uma regra de integridade dos dados")
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErroResponse> tratarErroGenerico() {
        return criarResposta(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno",
                List.of("Ocorreu um erro inesperado")
        );
    }

    private ResponseEntity<ApiErroResponse> criarResposta(HttpStatus status, String erro, List<String> mensagens) {
        ApiErroResponse response = new ApiErroResponse(
                LocalDateTime.now(),
                status.value(),
                erro,
                mensagens
        );

        return ResponseEntity.status(status).body(response);
    }
}
