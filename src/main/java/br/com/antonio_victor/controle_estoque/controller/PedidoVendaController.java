package br.com.antonio_victor.controle_estoque.controller;

import br.com.antonio_victor.controle_estoque.dto.DadosPedidoVendaDto;
import br.com.antonio_victor.controle_estoque.dto.ItemPedidoAtualizadoDto;
import br.com.antonio_victor.controle_estoque.dto.ItemPedidoDto;
import br.com.antonio_victor.controle_estoque.service.PedidoVendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pedido-de-venda")
public class PedidoVendaController {
    @Autowired
    PedidoVendaService pedidoVendaService;

    @PostMapping
    public ResponseEntity criar() {
        String msg = pedidoVendaService.criarPedidoVenda();
        return ResponseEntity.ok().body(msg);
    }
    @PostMapping("/{numPedido}/produtos/{numProduto}")
    public ResponseEntity inserirProdutos(@PathVariable Long numPedido, @PathVariable Long numProduto, @Valid @RequestBody ItemPedidoDto itemPedidoDto) {
        var pedido = pedidoVendaService.adicionarProdutos(numPedido, numProduto, itemPedidoDto);
        return ResponseEntity.ok().body(pedido);

    }
    @GetMapping
    public ResponseEntity<List<DadosPedidoVendaDto>> listarPedidos() {
        var pedidos = pedidoVendaService.listar();
        return ResponseEntity.ok().body(pedidos);
    }
    @GetMapping("/{numero}")
    public ResponseEntity listarPedidoPorNumero(@PathVariable Long numero) {
        var pedido = pedidoVendaService.listarPorNumero(numero);
        return ResponseEntity.ok().body(pedido);
    }
    @PutMapping("/{numero}")
    public ResponseEntity atualizarProdutos(@PathVariable Long numero, @RequestBody ItemPedidoAtualizadoDto itemPedidoAtualizadoDto) {
        var pedido = pedidoVendaService.atualizarItens(numero, itemPedidoAtualizadoDto);
        return ResponseEntity.ok().body(pedido);
    }
    @DeleteMapping("/{numero}")
    public ResponseEntity excluirPedido(@PathVariable Long numero) {
        var response = pedidoVendaService.deletar(numero);
        return ResponseEntity.ok().body(response);
    }
    @DeleteMapping("/{numPedido}/produtos/{numItem}")
    public ResponseEntity excluirItensPedido(@PathVariable Long numPedido, @PathVariable Long numItem) {
       var pedido =  pedidoVendaService.excluirItens(numPedido, numItem);
       return ResponseEntity.ok().body(pedido);
    }

}
