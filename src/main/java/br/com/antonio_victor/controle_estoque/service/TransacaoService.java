package br.com.antonio_victor.controle_estoque.service;

import br.com.antonio_victor.controle_estoque.dto.DadosTransacaoDto;
import br.com.antonio_victor.controle_estoque.model.Transacao;
import br.com.antonio_victor.controle_estoque.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository transacaoRepository;

    public List<DadosTransacaoDto> listar() {
        return transacaoRepository.findAll().stream().map(DadosTransacaoDto::new).toList();
    }
}
