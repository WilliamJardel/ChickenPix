package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.exception.StatusInvalidoException;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "transacao")
public class Transacao {

    @Id
    private String id;

    private double valor;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    @Enumerated(EnumType.STRING)
    private StatusTransacao status;

    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "conta_origem_id")
    private ContaBancaria origem;

    @ManyToOne
    @JoinColumn(name = "conta_destino_id")
    private ContaBancaria destino;

    protected Transacao() {}

    public Transacao(ContaBancaria origem, ContaBancaria destino, double valor, TipoTransacao tipo) {
        this.id = UUID.randomUUID().toString();
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
        this.tipo = tipo;
        this.status = StatusTransacao.PENDENTE;
        this.dataHora = LocalDateTime.now();
    }

    public void concluir() {
        this.status = StatusTransacao.CONCLUIDA;
    }

    public void cancelar() {
        if (this.status != StatusTransacao.PENDENTE) {
            throw new StatusInvalidoException("Somente transações pendentes podem ser canceladas!");
        }
        this.status = StatusTransacao.CANCELADA;
    }
}