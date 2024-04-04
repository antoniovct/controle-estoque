package br.com.antonio_victor.controle_estoque.controller;

import br.com.antonio_victor.controle_estoque.service.RelatorioEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("relatorio-estoque")
public class RelatorioEstoqueController {
    @Autowired
    private RelatorioEstoqueService relatorioEstoqueService;

    @GetMapping
    public ResponseEntity emitirRelatorio() {
        var relatorio = relatorioEstoqueService.emitirRelatorio();
        return ResponseEntity.ok().body(relatorio);
    }
}
