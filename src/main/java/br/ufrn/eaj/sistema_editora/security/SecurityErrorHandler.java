package br.ufrn.eaj.sistema_editora.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class SecurityErrorHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        escreverResposta(response, HttpStatus.UNAUTHORIZED, "Nao autorizado", "Token ausente ou invalido");
    }

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {
        escreverResposta(response, HttpStatus.FORBIDDEN, "Acesso negado", "Privilegio administrativo necessario");
    }

    private void escreverResposta(
            HttpServletResponse response,
            HttpStatus status,
            String erro,
            String mensagem
    ) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("""
                {"dataHora":"%s","status":%d,"erro":"%s","mensagens":["%s"]}""".formatted(
                LocalDateTime.now(),
                status.value(),
                escaparJson(erro),
                escaparJson(mensagem)
        ));
    }

    private String escaparJson(String valor) {
        return valor.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
