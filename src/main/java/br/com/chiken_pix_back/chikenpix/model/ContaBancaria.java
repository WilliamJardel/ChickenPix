package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.exception.ChaveNaoEncontradaException;
import br.com.chiken_pix_back.chikenpix.exception.ValorInvalidoException;
import br.com.chiken_pix_back.chikenpix.exception.ValorPixInvalidoException;
import lombok.Getter;
import java.util.HashMap;

public class ContaBancaria {

    private @Getter final String numeroConta;
    private @Getter final String numeroAgencia;
    private @Getter final String codigoBanco;
    private @Getter final String nomeBanco;
    private @Getter double saldo;
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

    public boolean realizarPix(TipoChavePix destino, double value) throws ChaveNaoEncontradaException, ValorPixInvalidoException {
        if(buscarChavePix(destino) == null) {
            throw new ChaveNaoEncontradaException(
                "Chave Pix não encontrada."
            );
        }
        if(value <= 0.00) {
            throw new ValorPixInvalidoException(
                    "Valor inválido inserido."
            );
        }
        creditar(value);
        return true;
    }
}
