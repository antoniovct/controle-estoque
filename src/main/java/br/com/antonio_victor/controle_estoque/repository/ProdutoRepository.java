package br.com.antonio_victor.controle_estoque.repository;

import br.com.antonio_victor.controle_estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
