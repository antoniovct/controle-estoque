package br.com.antonio_victor.controle_estoque.security;

import br.com.antonio_victor.controle_estoque.dto.UsuarioDto;
import br.com.antonio_victor.controle_estoque.model.Usuario;
import br.com.antonio_victor.controle_estoque.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody UsuarioDto usuarioDto) {
        var token = new UsernamePasswordAuthenticationToken(usuarioDto.usuario(), usuarioDto.senha());
        var user = authenticationManager.authenticate(token);
        var tokenJWT = tokenService.gerarToken((Usuario) user.getPrincipal());
        return ResponseEntity.ok(tokenJWT);
    }
}
