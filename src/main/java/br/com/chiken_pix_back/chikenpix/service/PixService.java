package br.com.chiken_pix_back.chikenpix.service;

import br.com.chiken_pix_back.chikenpix.model.Banco;
import br.com.chiken_pix_back.chikenpix.model.ChavePix;
import br.com.chiken_pix_back.chikenpix.model.ContaBancaria;

public class PixService {
    private Banco banco;

    public void realizarPix(ContaBancaria origem, String chaveDestino, double value) {
        ContaBancaria destino = banco.buscarConta(chaveDestino);

        origem.debitar(value);
        destino.creditar(value);
    }
}
