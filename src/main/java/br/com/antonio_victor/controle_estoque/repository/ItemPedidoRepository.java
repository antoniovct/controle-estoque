package br.com.antonio_victor.controle_estoque.repository;

import br.com.antonio_victor.controle_estoque.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
