package br.com.antonio_victor.controle_estoque.model;

import br.com.antonio_victor.controle_estoque.dto.ItemPedidoAtualizadoDto;
import br.com.antonio_victor.controle_estoque.dto.ItemPedidoDto;
import br.com.antonio_victor.controle_estoque.exceptions.InsufficientQuantityException;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "pedido_venda")
public class PedidoVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;
    private String data;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoVenda")
    private List<ItemPedido> produtos;
    private Double valorTotal;

    public PedidoVenda() {
        this.data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.produtos = new ArrayList<>();
        this.valorTotal = 0.00;
    }

    public void adicionarProdutos(Produto produto, ItemPedidoDto itemPedidoDto, PedidoVenda pedidoVenda) {
        if (itemPedidoDto.quantidade() <= produto.getQuantidadeEstoque()) {
            ItemPedido itemPedido = new ItemPedido(produto, itemPedidoDto, pedidoVenda);
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - itemPedido.getQuantidade());
            if (produto.getQuantidadeEstoque() <= 100) {
                produto.setEstoqueBaixo(true);
            }
            this.produtos.add(itemPedido);
            this.valorTotal += itemPedido.getValorTotal();
        }
    }

    public void atualizarProdutos(ItemPedidoAtualizadoDto itemPedidoAtualizadoDto) {
        Optional<ItemPedido> itemPedido = produtos.stream().filter(i -> i.getId().equals(itemPedidoAtualizadoDto.id())).findFirst();
        if (itemPedido.isPresent()) {
            ItemPedido item = itemPedido.get();
            if (itemPedidoAtualizadoDto.quantidade() <= item.getProduto().getQuantidadeEstoque()) {
                if (itemPedidoAtualizadoDto.quantidade() > item.getQuantidade()) {
                    int diferenca = itemPedidoAtualizadoDto.quantidade() - item.getQuantidade();
                    item.getProduto().setQuantidadeEstoque(item.getProduto().getQuantidadeEstoque() - diferenca);
                    if (item.getProduto().getQuantidadeEstoque() <= 100) {
                        item.getProduto().setEstoqueBaixo(true);
                    }
                } else {
                    int diferenca = item.getQuantidade() - itemPedidoAtualizadoDto.quantidade();
                    item.getProduto().setQuantidadeEstoque(item.getProduto().getQuantidadeEstoque() + diferenca);
                    if (item.getProduto().getQuantidadeEstoque() > 100) {
                        item.getProduto().setEstoqueBaixo(false);
                    }
                }

                double valorTotalAntigoItem = item.getValorTotal();
                double valorTotalAtualizadoItem = item.atualizar(itemPedidoAtualizadoDto);
                double diferenca = valorTotalAntigoItem - valorTotalAtualizadoItem;
                if (diferenca > 0) {
                    this.valorTotal -= diferenca;
                } else {
                    this.valorTotal += Math.abs(diferenca);
                }
            }else {throw new InsufficientQuantityException("A quantidade é maior do que o estoque.");
            }
        }
    }

    public List<ItemPedido> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ItemPedido> produtos) {
        this.produtos = produtos;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "PedidoVenda{" +
                "numero=" + numero +
                ", data='" + data + '\'' +
                ", produtos=" + produtos +
                ", valorTotal=" + valorTotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoVenda that = (PedidoVenda) o;
        return Objects.equals(numero, that.numero) && Objects.equals(data, that.data) && Objects.equals(produtos, that.produtos) && Objects.equals(valorTotal, that.valorTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, data, produtos, valorTotal);
    }
}
