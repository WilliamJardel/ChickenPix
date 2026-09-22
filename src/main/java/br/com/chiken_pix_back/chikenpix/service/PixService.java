package br.com.chiken_pix_back.chikenpix.service;

import br.com.chiken_pix_back.chikenpix.enumerations.TipoTransacao;
import br.com.chiken_pix_back.chikenpix.exception.ChaveNaoEncontradaException;
import br.com.chiken_pix_back.chikenpix.exception.ValorPixInvalidoException;
import br.com.chiken_pix_back.chikenpix.model.Banco;
import br.com.chiken_pix_back.chikenpix.model.ContaBancaria;
import br.com.chiken_pix_back.chikenpix.model.Transacao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PixService {
    private Banco banco;

    public PixService(Banco banco){
        this.banco = banco;
    }

    public void realizarPix(ContaBancaria origem, String chaveDestino, BigDecimal valor) {

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorPixInvalidoException(
                    "Error: Valor inválido para realizar Pix."
            );
        }

        ContaBancaria destino = banco.buscarConta(chaveDestino);


        if (destino == null){
            throw new ChaveNaoEncontradaException(
              "Error: Chave Pix não encontrada."
            );
        }

        Transacao transacao = new Transacao(origem, destino, valor, TipoTransacao.PIX_ENVIADO);
        banco.addTransacao(transacao);
    }
}
