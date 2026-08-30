package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.exception.*;
import lombok.Getter;
import java.util.HashMap;

public class ContaBancaria {

    private @Getter final String numeroConta;
    private @Getter final String numeroAgencia;
    private @Getter final String codigoBanco;
    private @Getter final String nomeBanco;
    private @Getter double saldo;
    private @Getter StatusConta status;
    private final HashMap<TipoChavePix, ChavePix> chavesPix;

    public ContaBancaria(String numeroConta){
        this.numeroConta = numeroConta;
        this.numeroAgencia = "";
        this.codigoBanco = "";
        this.nomeBanco = "ChikenPIX";
        this.saldo = 0.00;
        this.status = StatusConta.ATIVA;
        this.chavesPix = new HashMap<TipoChavePix, ChavePix>(5);
    }

    public void encerrarConta() throws SaldoNaoZeradoException {
        if(this.saldo != 0){
            throw new SaldoNaoZeradoException("Erro ao encerrar conta: seu saldo não está zerado!");
        }
        this.status = StatusConta.DESATIVADA;
    }

    public void debitar(double valor) throws ValorPixInvalidoException, SaldoInsuficienteException {
        if (getSaldo() - valor < 0){
            throw new SaldoInsuficienteException(
                    "Error: Saldo insuficiente para realizar Pix."
            );
        }

        if (valor <= 0.00){
            throw new ValorPixInvalidoException(
                    "Error: Valor inválido para realizar Pix."
            );
        }
        this.saldo -= valor;
    }

    public void creditar(double valor){
        this.saldo += valor;
    }

    public void addChavePix(TipoChavePix tipoChave, ChavePix chave) throws ChavePixJaCadastradaException {
        if (buscarChavePix(tipoChave) != null){
            throw new ChavePixJaCadastradaException(
                    "Error: Chave Pix já cadastrada."
            );
        }
        this.chavesPix.put(tipoChave, chave);
    }

    public ChavePix buscarChavePix(TipoChavePix tipoChave){
        return chavesPix.get(tipoChave);
    }

    public ChavePix rmChavePix(TipoChavePix tipoChave) throws ChaveNaoEncontradaException{
        ChavePix rmChave = buscarChavePix(tipoChave);
        if(rmChave == null) {
            throw new ChaveNaoEncontradaException(
                    "Error: Chave Pix não encontrada."
            );
        }

        this.chavesPix.remove(tipoChave);
        return rmChave;
    }

}
