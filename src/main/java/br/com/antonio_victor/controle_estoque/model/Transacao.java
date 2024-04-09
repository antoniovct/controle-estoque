package br.com.antonio_victor.controle_estoque.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "transacao")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data;
    private int quantidade;
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipoTransacao;
    @ManyToOne
    private Produto produto;
    public Transacao() {

    }
    public Transacao(Produto produto, int quantidade, TipoTransacao tipoTransacao) {
        this.data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        this.produto = produto;
        this.quantidade = quantidade;
        this.tipoTransacao = tipoTransacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return quantidade == transacao.quantidade && Objects.equals(id, transacao.id) && Objects.equals(data, transacao.data) && tipoTransacao == transacao.tipoTransacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, quantidade, tipoTransacao);
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", quantidade=" + quantidade +
                ", tipoTransacao=" + tipoTransacao +
                '}';
    }
}
