package br.ufrn.eaj.sistema_editora.controller;

import br.ufrn.eaj.sistema_editora.dto.AuthRequestDTO;
import br.ufrn.eaj.sistema_editora.dto.TokenResponseDTO;
import br.ufrn.eaj.sistema_editora.errorhandling.CredenciaisInvalidasException;
import br.ufrn.eaj.sistema_editora.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final String PAPEL_ADMIN = "ADMIN";

    private final TokenService tokenService;

    @Value("${sistema.security.admin.username:admin}")
    private String adminUsuario;

    @Value("${sistema.security.admin.password:admin}")
    private String adminSenha;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public ResponseEntity<TokenResponseDTO> gerarToken(@Valid @RequestBody AuthRequestDTO request) {
        if (!adminUsuario.equals(request.usuario()) || !adminSenha.equals(request.senha())) {
            throw new CredenciaisInvalidasException("Usuario ou senha invalidos");
        }

        String token = tokenService.gerarToken(request.usuario(), PAPEL_ADMIN);

        return ResponseEntity.ok(new TokenResponseDTO("Bearer", token, tokenService.getDuracaoSegundos()));
    }
}
