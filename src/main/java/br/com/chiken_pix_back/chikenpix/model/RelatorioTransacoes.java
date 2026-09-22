package br.com.chiken_pix_back.chikenpix.model;

import java.math.BigDecimal;
import java.util.List;

public record RelatorioTransacoes(
        List<Transacao> transacoes,
        BigDecimal totalEnviado,
        BigDecimal totalRecebido,
        int quantidadeTransacoes
) {}


//Usamos record porque essa classe só carrega dados prontos (resultado de um cálculo),
//sem lógica própria — o Java já gera automaticamente construtor, métodos de acesso e
//imutabilidade, evitando código repetitivo.