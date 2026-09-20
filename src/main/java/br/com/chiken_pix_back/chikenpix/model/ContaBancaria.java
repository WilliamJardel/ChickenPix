package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.enumerations.StatusConta;
import br.com.chiken_pix_back.chikenpix.enumerations.TipoChavePix;
import br.com.chiken_pix_back.chikenpix.exception.*;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "contas_bancarias")
public class ContaBancaria {

    private static final String NUMERO_AGENCIA = "0001001";
    private static final String CODIGO_BANCO = "D022NSJZ02012";
    private static final String NOME_BANCO = "ChikenPIX";

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private @Setter Usuario usuario;

    @Id
    private String numeroConta;

    @Column(precision = 15, scale = 2)
    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    private StatusConta status;

    @OneToMany(mappedBy = "contaBancaria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChavePix> chavesPix = new ArrayList<>();

    protected ContaBancaria() {}

    public ContaBancaria(String numeroConta) {
        this.numeroConta = numeroConta;
        this.saldo = BigDecimal.ZERO;
        this.status = StatusConta.ATIVA;
    }

    public String getNumeroAgencia() { return NUMERO_AGENCIA; }
    public String getCodigoBanco() { return CODIGO_BANCO; }
    public String getNomeBanco() { return NOME_BANCO; }

    public void encerrarConta() {
        if (this.saldo.compareTo(BigDecimal.ZERO) != 0) {
            throw new SaldoNaoZeradoException("Erro ao encerrar conta: seu saldo não está zerado!");
        }
        this.status = StatusConta.DESATIVADA;
    }

    public void debitar(BigDecimal valor) {
        if (this.status == StatusConta.DESATIVADA) {
            throw new ContaDesativadaException("Error: Conta Desativada, operação falhou.");
        }
        if (this.saldo.compareTo(valor) <= 0) {
            throw new SaldoInsuficienteException("Error: Saldo insuficiente para realizar Pix.");
        }
        this.saldo = this.saldo.subtract(valor);
    }

    public void creditar(BigDecimal valor) {
        if (this.status == StatusConta.DESATIVADA) {
            throw new ContaDesativadaException("Error: Conta Desativada, operação falhou.");
        }
        this.saldo = this.saldo.add(valor);
    }

    public void addChavePix(TipoChavePix tipoChave, String valorChave) {
        if (tipoChave == TipoChavePix.ALEATORIA) {
            throw new IllegalArgumentException("Use gerarChaveAleatoria() para chaves do tipo ALEATORIA.");
        }
        if (buscarChavePix(tipoChave) != null) {
            throw new ChavePixJaCadastradaException("Error: Chave Pix já cadastrada.");
        }
        ChavePixValidation.validar(tipoChave, valorChave);
        chavesPix.add(new ChavePix(tipoChave, valorChave, this));
    }

    public ChavePix buscarChavePix(TipoChavePix tipoChave) {
        return chavesPix.stream()
                .filter(c -> c.getTipoChave() == tipoChave)
                .findFirst()
                .orElse(null);
    }

    public ChavePix rmChavePix(TipoChavePix tipoChave) {
        ChavePix rmChave = buscarChavePix(tipoChave);
        if (rmChave == null) {
            throw new ChaveNaoEncontradaException("Error: Chave Pix não encontrada.");
        }
        chavesPix.remove(rmChave);
        return rmChave;
    }

    public ChavePix gerarChaveAleatoria() {
        if (buscarChavePix(TipoChavePix.ALEATORIA) != null) {
            throw new ChavePixJaCadastradaException("Esta conta já possui uma chave aleatória.");
        }
        String chaveGerada = UUID.randomUUID().toString();
        ChavePix novaChave = new ChavePix(TipoChavePix.ALEATORIA, chaveGerada, this);
        chavesPix.add(novaChave);
        return novaChave;
    }
}