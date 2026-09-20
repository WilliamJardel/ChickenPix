package br.com.chiken_pix_back.chikenpix.service;

import br.com.chiken_pix_back.chikenpix.model.Banco;
import br.com.chiken_pix_back.chikenpix.model.ContaBancaria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ContaService {
    private Banco banco;

    public ContaService(Banco banco){
        this.banco = banco;
    }

    public BigDecimal consultarSaldo(String numeroConta){
        ContaBancaria conta = banco.buscarConta(numeroConta);
        return conta.getSaldo();
    }
}
