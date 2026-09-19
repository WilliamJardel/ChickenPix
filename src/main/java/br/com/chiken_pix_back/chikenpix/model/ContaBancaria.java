package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.exception.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Entity
@Table(name = "contas_bancarias")
public class ContaBancaria {

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private @Setter Usuario usuario;

    @Id
    private String numeroConta = "";

    private final String numeroAgencia = "0001001";
    private final String codigoBanco = "D022NSJZ02012";
    private final String nomeBanco = "ChikenPIX";
    private double saldo;
    private StatusConta status;
    private HashMap<TipoChavePix, ChavePix> chavesPix = null;

    protected ContaBancaria(){};

    public ContaBancaria(String numeroConta){
        this.numeroConta = numeroConta;
        this.saldo = 0.00;
        this.status = StatusConta.ATIVA;
        this.chavesPix = new HashMap<TipoChavePix, ChavePix>(5);
    }

    public void encerrarConta() {
        if(this.saldo != 0){
            throw new SaldoNaoZeradoException("Erro ao encerrar conta: seu saldo não está zerado!");
        }
        this.status = StatusConta.DESATIVADA;
    }

    public void debitar(double valor) {
        if (this.status == StatusConta.DESATIVADA){
            throw new ContaDesativadaException(
              "Error: Conta Desativada, operação falhou."
            );
        }

        if (getSaldo() < valor){
            throw new SaldoInsuficienteException(
                    "Error: Saldo insuficiente para realizar Pix."
            );
        }

        this.saldo -= valor;
    }

    public void creditar(double valor){
        if (this.status == StatusConta.DESATIVADA){
            throw new ContaDesativadaException(
                    "Error: Conta Desativada, operação falhou."
            );
        }
        this.saldo += valor;
    }

    public void addChavePix(TipoChavePix tipoChave, ChavePix chave) {
        if (buscarChavePix(tipoChave) != null){
            throw new ChavePixJaCadastradaException(
                    "Error: Chave Pix já cadastrada."
            );
        }
        chave.validar();
        this.chavesPix.put(tipoChave, chave);
    }

    public ChavePix buscarChavePix(TipoChavePix tipoChave){
        return chavesPix.get(tipoChave);
    }

    public ChavePix rmChavePix(TipoChavePix tipoChave) {
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
