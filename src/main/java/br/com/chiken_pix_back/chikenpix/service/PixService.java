package br.com.chiken_pix_back.chikenpix.service;

import br.com.chiken_pix_back.chikenpix.exception.ChaveNaoEncontradaException;
import br.com.chiken_pix_back.chikenpix.exception.SaldoInsuficienteException;
import br.com.chiken_pix_back.chikenpix.exception.ValorPixInvalidoException;
import br.com.chiken_pix_back.chikenpix.model.Banco;
import br.com.chiken_pix_back.chikenpix.model.ChavePix;
import br.com.chiken_pix_back.chikenpix.model.ContaBancaria;
import org.springframework.stereotype.Service;

@Service
public class PixService {
    private Banco banco;

    public PixService(Banco banco){
        this.banco = banco;
    }

    public void realizarPix(ContaBancaria origem, String chaveDestino, double valor) {
        ContaBancaria destino = banco.buscarConta(chaveDestino);

        if (destino == null){
            throw new ChaveNaoEncontradaException(
              "Error: Chave Pix não encontrada."
            );
        }

        origem.debitar(valor);
        destino.creditar(valor);
    }
}
