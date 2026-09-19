package br.com.chiken_pix_back.chikenpix.model;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Transacao {
    private final String id;
    private final double valor;
    private TipoTransacao tipo;
    private StatusTransacao status;
    private final LocalDateTime dateHora;
    private final ContaBancaria origem;
    private final ContaBancaria destino;

    public Transacao(ContaBancaria origem, ContaBancaria destino, double valor, TipoTransacao tipo){
        this.id = UUID.randomUUID().toString();
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
        this.status = StatusTransacao.PENDENTE;
        this.tipo = tipo;
        this.dateHora = LocalDateTime.now();
    }



}
