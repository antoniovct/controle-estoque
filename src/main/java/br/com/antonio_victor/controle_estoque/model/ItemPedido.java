package br.com.antonio_victor.controle_estoque.model;


import br.com.antonio_victor.controle_estoque.dto.ItemPedidoAtualizadoDto;
import br.com.antonio_victor.controle_estoque.dto.ItemPedidoDto;
import br.com.antonio_victor.controle_estoque.dto.ProdutoDto;
import br.com.antonio_victor.controle_estoque.exceptions.InsufficientQuantityException;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "item_pedido")
public class ItemPedido{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Produto produto;
    private Integer quantidade;
    private Double valorUnitario;
    private Double valorTotal = 0.00;
    @ManyToOne
    private PedidoVenda pedidoVenda;



    public ItemPedido() {
    }
    public ItemPedido(Produto produto, ItemPedidoDto itemPedidoDto, PedidoVenda pedidoVenda) {
        this.produto = produto;
        this.quantidade = itemPedidoDto.quantidade();
        this.valorUnitario = produto.getPrecoVenda();
        this.valorTotal+= produto.getPrecoVenda() * itemPedidoDto.quantidade();
        this.pedidoVenda = pedidoVenda;
    }
    public double atualizar(ItemPedidoAtualizadoDto itemPedidoAtualizadoDto) {
            this.valorUnitario = itemPedidoAtualizadoDto.valorUnitario();
            this.quantidade = itemPedidoAtualizadoDto.quantidade();
            this.valorTotal = itemPedidoAtualizadoDto.valorUnitario() * itemPedidoAtualizadoDto.quantidade();
            return this.valorTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public PedidoVenda getPedidoVenda() {
        return pedidoVenda;
    }

    public void setPedidoVenda(PedidoVenda pedidoVenda) {
        this.pedidoVenda = pedidoVenda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(id, that.id) && Objects.equals(produto, that.produto) && Objects.equals(quantidade, that.quantidade) && Objects.equals(valorUnitario, that.valorUnitario) && Objects.equals(valorTotal, that.valorTotal) && Objects.equals(pedidoVenda, that.pedidoVenda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, produto, quantidade, valorUnitario, valorTotal, pedidoVenda);
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", valorUnitario=" + valorUnitario +
                ", valorTotal=" + valorTotal +
                ", pedidoVenda=" + pedidoVenda +
                '}';
    }
}
