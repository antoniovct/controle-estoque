package br.com.antonio_victor.controle_estoque.service;

import br.com.antonio_victor.controle_estoque.dto.DadosProdutoDto;
import br.com.antonio_victor.controle_estoque.dto.EntradaProdutoDto;
import br.com.antonio_victor.controle_estoque.dto.ProdutoAtualizadoDto;
import br.com.antonio_victor.controle_estoque.dto.ProdutoDto;
import br.com.antonio_victor.controle_estoque.model.Produto;
import br.com.antonio_victor.controle_estoque.model.TipoTransacao;
import br.com.antonio_victor.controle_estoque.model.Transacao;
import br.com.antonio_victor.controle_estoque.repository.ProdutoRepository;
import br.com.antonio_victor.controle_estoque.repository.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;

    @Transactional
    public DadosProdutoDto cadastrar(ProdutoDto produtoDto) {
        Produto produto = new Produto(produtoDto);
        produtoRepository.save(produto);
        Transacao transacao = new Transacao(produto,produtoDto.quantidadeEstoque(), TipoTransacao.ENTRADA);
        transacaoRepository.save(transacao);
        return new DadosProdutoDto(produto);
    }
    @Transactional
    public DadosProdutoDto entrada(EntradaProdutoDto entradaProdutoDto) {
        Produto produto = produtoRepository.getReferenceById(entradaProdutoDto.codigo());
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + entradaProdutoDto.quantidade());
        Transacao transacao = new Transacao(produto,entradaProdutoDto.quantidade(), TipoTransacao.ENTRADA);
        transacaoRepository.save(transacao);
        return new DadosProdutoDto(produto);
    }
    public List<DadosProdutoDto> listar() {
        return produtoRepository.findAll()
                .stream().map(DadosProdutoDto::new).toList();
    }
    public DadosProdutoDto listarPorCodigo(Long codigo) {
        Produto produto = produtoRepository.getReferenceById(codigo);
        return new DadosProdutoDto(produto);
    }
    @Transactional
    public DadosProdutoDto atualizar(Long codigo, ProdutoAtualizadoDto produtoDto) {
        Produto produto = produtoRepository.getReferenceById(codigo);
        produto.atualizarProduto(produtoDto);
        return new DadosProdutoDto(produto);
    }
    @Transactional
    public String excluir(Long codigo) {
        Produto produto = produtoRepository.getReferenceById(codigo);
        produtoRepository.delete(produto);
        return "Produto excluído com sucesso!";
    }
}
