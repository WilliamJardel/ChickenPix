package br.com.chiken_pix_back.chikenpix.service;

import br.com.chiken_pix_back.chikenpix.exception.ChaveNaoEncontradaException;
import br.com.chiken_pix_back.chikenpix.exception.SaldoInsuficienteException;
import br.com.chiken_pix_back.chikenpix.exception.ValorPixInvalidoException;
import br.com.chiken_pix_back.chikenpix.model.Banco;
import br.com.chiken_pix_back.chikenpix.model.ChavePix;
import br.com.chiken_pix_back.chikenpix.model.ContaBancaria;

public class PixService {
    private Banco banco;

    public void realizarPix(ContaBancaria origem, String chaveDestino, double value) throws ValorPixInvalidoException, ChaveNaoEncontradaException, SaldoInsuficienteException {
        if (origem.getSaldo() - value < 0){
            throw new SaldoInsuficienteException(
              "Error: Saldo insuficiente para realizar Pix."
            );
        }

        if (value <= 0.00){
            throw new ValorPixInvalidoException(
              "Valor inválido para realizar Pix."
            );
        }
        ContaBancaria destino = banco.buscarConta(chaveDestino);

        if (destino == null){
            throw new ChaveNaoEncontradaException(
              "Chave Pix não encontrada."
            );
        }

        origem.debitar(value);
        destino.creditar(value);
    }
}
