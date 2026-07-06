package br.ufrn.eaj.sistema_editora.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.Base64;
import java.util.Optional;

@Service
public class TokenService {

    private static final String ALGORITMO = "HmacSHA256";

    @Value("${sistema.security.token.secret:segredo-local-sistema-editora}")
    private String segredo;

    @Value("${sistema.security.token.expiration-seconds:3600}")
    private long duracaoSegundos;

    public String gerarToken(String usuario, String papel) {
        long expiraEm = Instant.now().plusSeconds(duracaoSegundos).getEpochSecond();
        String payload = usuario + "|" + papel + "|" + expiraEm;
        String payloadBase64 = encode(payload.getBytes(StandardCharsets.UTF_8));
        String assinatura = assinar(payloadBase64);

        return payloadBase64 + "." + assinatura;
    }

    public long getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public Optional<TokenAutenticado> validarToken(String token) {
        try {
            if (token == null || token.isBlank()) {
                return Optional.empty();
            }

            String[] partes = token.split("\\.");
            if (partes.length != 2 || !assinaturaValida(partes[0], partes[1])) {
                return Optional.empty();
            }

            String payload = new String(Base64.getUrlDecoder().decode(partes[0]), StandardCharsets.UTF_8);
            String[] dados = payload.split("\\|");
            if (dados.length != 3) {
                return Optional.empty();
            }

            long expiraEm = Long.parseLong(dados[2]);
            if (Instant.now().getEpochSecond() >= expiraEm) {
                return Optional.empty();
            }

            return Optional.of(new TokenAutenticado(dados[0], dados[1]));
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }

    private boolean assinaturaValida(String payloadBase64, String assinaturaRecebida) {
        String assinaturaEsperada = assinar(payloadBase64);
        return MessageDigest.isEqual(
                assinaturaEsperada.getBytes(StandardCharsets.UTF_8),
                assinaturaRecebida.getBytes(StandardCharsets.UTF_8)
        );
    }

    private String assinar(String payloadBase64) {
        try {
            Mac mac = Mac.getInstance(ALGORITMO);
            mac.init(new SecretKeySpec(segredo.getBytes(StandardCharsets.UTF_8), ALGORITMO));
            return encode(mac.doFinal(payloadBase64.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception ex) {
            throw new IllegalStateException("Falha ao assinar token");
        }
    }

    private String encode(byte[] bytes) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
