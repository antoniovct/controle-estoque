package br.com.antonio_victor.controle_estoque.controller;

import br.com.antonio_victor.controle_estoque.dto.UsuarioDto;
import br.com.antonio_victor.controle_estoque.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cadastro")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity cadastrarUsuario(@RequestBody UsuarioDto usuarioDto) {
        var usuario = usuarioService.cadastrar(usuarioDto);
        return ResponseEntity.ok().body(usuario);
    }
}
