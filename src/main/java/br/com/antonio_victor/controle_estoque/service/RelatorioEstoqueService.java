package br.com.antonio_victor.controle_estoque.service;

import br.com.antonio_victor.controle_estoque.dto.DadosRelatorioEstoqueDto;
import br.com.antonio_victor.controle_estoque.model.Produto;
import br.com.antonio_victor.controle_estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatorioEstoqueService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public DadosRelatorioEstoqueDto emitirRelatorio() {
        var quantidadeProdutos = produtoRepository.findAll().size();
        var valorTotalEmEstoque = produtoRepository.findAll().stream()
                .map(p -> p.getPrecoVenda() * p.getQuantidadeEstoque())
                .reduce(0.0, (acc, x) -> acc + x);
        var produtosEstoqueBaixo = produtoRepository.findAll().stream().filter(p -> p.getEstoqueBaixo().equals(true)).toList();
        return new DadosRelatorioEstoqueDto(quantidadeProdutos, valorTotalEmEstoque, produtosEstoqueBaixo);
    }

}
