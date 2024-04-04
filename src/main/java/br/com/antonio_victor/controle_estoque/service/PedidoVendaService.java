package br.com.antonio_victor.controle_estoque.service;

import br.com.antonio_victor.controle_estoque.dto.DadosPedidoVendaDto;
import br.com.antonio_victor.controle_estoque.dto.ItemPedidoAtualizadoDto;
import br.com.antonio_victor.controle_estoque.dto.ItemPedidoDto;
import br.com.antonio_victor.controle_estoque.exceptions.InsufficientQuantityException;
import br.com.antonio_victor.controle_estoque.model.*;
import br.com.antonio_victor.controle_estoque.repository.ItemPedidoRepository;
import br.com.antonio_victor.controle_estoque.repository.PedidoVendaRepository;
import br.com.antonio_victor.controle_estoque.repository.ProdutoRepository;
import br.com.antonio_victor.controle_estoque.repository.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoVendaService {
    @Autowired
    private PedidoVendaRepository pedidoVendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;
    @Transactional
    public String criarPedidoVenda() {
        PedidoVenda pedidoVenda = new PedidoVenda();
        pedidoVendaRepository.save(pedidoVenda);
        return "Pedido de venda criado! Número do pedido: " + pedidoVenda.getNumero();
    }
    @Transactional
    public DadosPedidoVendaDto adicionarProdutos(Long numPedido, Long numProduto, ItemPedidoDto itemPedidoDto) {
        PedidoVenda pedidoVenda = pedidoVendaRepository.getReferenceById(numPedido);
        Produto produto = produtoRepository.getReferenceById(numProduto);
        if (itemPedidoDto.quantidade() <= produto.getQuantidadeEstoque()) {
            pedidoVenda.adicionarProdutos(produto, itemPedidoDto, pedidoVenda);
            Transacao transacao = new Transacao(produto,itemPedidoDto.quantidade(), TipoTransacao.SAIDA);
            transacaoRepository.save(transacao);
            return new DadosPedidoVendaDto(pedidoVenda);
        }else {
            throw new InsufficientQuantityException("A quantidade é maior do que o estoque.");
        }

    }
    public List<DadosPedidoVendaDto> listar() {
        return pedidoVendaRepository.findAll().stream()
                .map(DadosPedidoVendaDto::new)
                .toList();
    }
    public DadosPedidoVendaDto listarPorNumero(Long numero) {
        return new DadosPedidoVendaDto(pedidoVendaRepository.getReferenceById(numero));
    }
    @Transactional
    public String deletar(Long numero) {
        PedidoVenda pedidoVenda = pedidoVendaRepository.getReferenceById(numero);
        pedidoVendaRepository.delete(pedidoVenda);
        return "Pedido excluído com sucesso!";
    }
    @Transactional
    public DadosPedidoVendaDto atualizarItens(Long numeroPedido, ItemPedidoAtualizadoDto itemPedidoAtualizadoDto) {
        PedidoVenda pedidoVenda = pedidoVendaRepository.getReferenceById(numeroPedido);
        pedidoVenda.atualizarProdutos(itemPedidoAtualizadoDto);
        return new DadosPedidoVendaDto(pedidoVenda);
    }
    @Transactional
    public DadosPedidoVendaDto excluirItens(Long numeroPedido, Long numeroItem) {
        PedidoVenda pedidoVenda = pedidoVendaRepository.getReferenceById(numeroPedido);
        ItemPedido itemPedido = itemPedidoRepository.getReferenceById(numeroItem);
        itemPedidoRepository.delete(itemPedido);
        return new DadosPedidoVendaDto(pedidoVenda);
    }
}
