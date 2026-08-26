package br.com.chiken_pix_back.chikenpix.model;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.time.LocalDateTime;

public class Transacao {

    @Getter
    private String id;
    @Setter
    @Getter
    private double valor;
    @Getter
    private TipoTransacao tipo;
    @Getter
    private LocalDateTime dateTime;
    @Setter
    @Getter
    private StatusTransacao status;
    @Getter
    private ContaBancaria origem;
    @Getter
    private ContaBancaria destino;

    public Transacao(double valor, ContaBancaria origem, ContaBancaria destino, TipoTransacao tipo){
        this.id = UUID.randomUUID().toString();
        this.valor = valor;
        this.origem = origem;
        this.destino = destino;
        this.tipo = tipo;
        this.dateTime = LocalDateTime.now();
        this.status = StatusTransacao.PENDENTE; // sempre vai ta pendente
    }
}
