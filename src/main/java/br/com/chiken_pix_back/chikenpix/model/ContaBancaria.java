package br.com.chiken_pix_back.chikenpix.model;

import java.util.HashMap;

public class ContaBancaria {

    private final String numeroConta;
    private final String numeroAgencia;
    private final String codigoBanco;
    private final String nomeBanco;
    private double saldo;
    private final HashMap<TipoChavePix, ChavePix> chavesPix;

    public ContaBancaria(String numeroConta){
        this.numeroConta = numeroConta;
        this.numeroAgencia = "";
        this.codigoBanco = "";
        this.nomeBanco = "ChikenPIX";
        this.saldo = 0.00;
        this.chavesPix = new HashMap<TipoChavePix, ChavePix>(5);
    }

    public void debitar(double valor){
        this.saldo -= valor;
    }

    public void creditar(double valor){
        this.saldo += valor;
    }

    public void addChavePix(TipoChavePix tipoChave, ChavePix chave){
        this.chavesPix.put(tipoChave, chave);
    }

    public ChavePix buscarChavePix(TipoChavePix tipoChave){
        return chavesPix.get(tipoChave);
    }

    public ChavePix rmChavePix(TipoChavePix tipoChave){
        ChavePix rmChave = buscarChavePix(tipoChave);
        this.chavesPix.remove(tipoChave);
        return rmChave;
    }
}
