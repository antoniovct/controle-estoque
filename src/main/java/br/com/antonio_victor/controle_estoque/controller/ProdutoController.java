package br.com.antonio_victor.controle_estoque.controller;

import br.com.antonio_victor.controle_estoque.dto.DadosProdutoDto;
import br.com.antonio_victor.controle_estoque.dto.EntradaProdutoDto;
import br.com.antonio_victor.controle_estoque.dto.ProdutoAtualizadoDto;
import br.com.antonio_victor.controle_estoque.dto.ProdutoDto;
import br.com.antonio_victor.controle_estoque.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("produto")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity cadastrarProduto(@Valid @RequestBody ProdutoDto produtoDto, UriComponentsBuilder uriBuilder) {
        var produto = produtoService.cadastrar(produtoDto);
        var uri = uriBuilder.path("/produto/{codigo}").buildAndExpand(produto.codigo()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }
    @PostMapping("/entrada")
    public ResponseEntity entradaDeProduto(@Valid @RequestBody EntradaProdutoDto entradaProdutoDto) {
        var produto = produtoService.entrada(entradaProdutoDto);
        return ResponseEntity.ok().body(produto);
    }
    @GetMapping
    public ResponseEntity<List<DadosProdutoDto>> listarProdutos() {
        var produtos = produtoService.listar();
        return ResponseEntity.ok().body(produtos);
    }
    @GetMapping("/{codigo}")
    public ResponseEntity listarProdutoPorCodigo(@PathVariable Long codigo) {
        var produto = produtoService.listarPorCodigo(codigo);
        return ResponseEntity.ok().body(produto);
    }
    @PutMapping("/{codigo}")
    public ResponseEntity atualizarProdutos(@PathVariable Long codigo, @Valid @RequestBody ProdutoAtualizadoDto produtoDto) {
        DadosProdutoDto dadosProdutoDto = produtoService.atualizar(codigo, produtoDto);
        return ResponseEntity.ok().body(dadosProdutoDto);
    }
    @DeleteMapping("/{codigo}")
    public ResponseEntity excluirProduto(@PathVariable Long codigo) {
        String response = produtoService.excluir(codigo);
        return ResponseEntity.ok().body(response);
    }
}
