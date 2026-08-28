package br.com.chiken_pix_back.chikenpix.model;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.time.LocalDateTime;

public class Transacao {

    private @Getter String id;
    @Getter @Setter private double valor;
    private @Getter TipoTransacao tipo;
    private @Getter LocalDateTime dateTime;
    @Getter @Setter private StatusTransacao status;
    private @Getter ContaBancaria origem;
    private @Getter ContaBancaria destino;

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
