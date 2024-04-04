package br.com.antonio_victor.controle_estoque.controller;

import br.com.antonio_victor.controle_estoque.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transacao")
public class TransacaoController {
    @Autowired
    private TransacaoService transacaoService;

    @GetMapping
    public ResponseEntity listarTransacoes() {
        var transacoes = transacaoService.listar();
        return ResponseEntity.ok().body(transacoes);
    }
}
