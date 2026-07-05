package br.ufrn.eaj.sistema_editora.errorhandling;

import java.time.LocalDateTime;
import java.util.List;

public record ApiErroResponse(
        LocalDateTime dataHora,
        int status,
        String erro,
        List<String> mensagens
) {
}
