package br.com.antonio_victor.controle_estoque.repository;

import br.com.antonio_victor.controle_estoque.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
