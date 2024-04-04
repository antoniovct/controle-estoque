package br.com.antonio_victor.controle_estoque.repository;

import br.com.antonio_victor.controle_estoque.model.PedidoVenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoVendaRepository extends JpaRepository<PedidoVenda, Long> {
}
