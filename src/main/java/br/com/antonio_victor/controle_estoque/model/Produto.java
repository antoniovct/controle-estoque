package br.com.antonio_victor.controle_estoque.model;

import br.com.antonio_victor.controle_estoque.dto.ProdutoAtualizadoDto;
import br.com.antonio_victor.controle_estoque.dto.ProdutoDto;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String descricao;
    private Double precoCusto;
    private Double precoVenda;
    private Integer quantidadeEstoque;
    private Boolean estoqueBaixo;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Produto() {

    }

    public Produto(ProdutoDto produtoDto) {
        this.descricao = produtoDto.descricao();
        this.precoCusto = produtoDto.precoCusto();
        this.precoVenda = produtoDto.precoVenda();
        this.quantidadeEstoque = produtoDto.quantidadeEstoque();
        this.categoria = produtoDto.categoria();
        this.estoqueBaixo = false;
    }
    public void atualizarProduto(ProdutoAtualizadoDto produtoDto) {
        if (produtoDto.descricao() != null){
            this.descricao = produtoDto.descricao();
        }else if (produtoDto.precoCusto() != null){
            this.precoCusto = produtoDto.precoCusto();
        }else if (produtoDto.precoVenda() != null){
            this.precoVenda = produtoDto.precoVenda();
        }else if (produtoDto.quantidadeEstoque() != null){
            this.quantidadeEstoque = produtoDto.quantidadeEstoque();
        }
        if (this.quantidadeEstoque <= 100) {
            this.estoqueBaixo = true;
        }else {
            this.estoqueBaixo = false;
        }
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(Double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Boolean getEstoqueBaixo() {
        return estoqueBaixo;
    }

    public void setEstoqueBaixo(Boolean estoqueBaixo) {
        this.estoqueBaixo = estoqueBaixo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(codigo, produto.codigo) && Objects.equals(descricao, produto.descricao) && Objects.equals(precoCusto, produto.precoCusto) && Objects.equals(precoVenda, produto.precoVenda) && Objects.equals(quantidadeEstoque, produto.quantidadeEstoque) && Objects.equals(estoqueBaixo, produto.estoqueBaixo) && categoria == produto.categoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, descricao, precoCusto, precoVenda, quantidadeEstoque, estoqueBaixo, categoria);
    }
}
