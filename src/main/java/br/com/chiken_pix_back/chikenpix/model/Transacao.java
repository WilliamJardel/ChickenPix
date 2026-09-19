package br.com.chiken_pix_back.chikenpix.model;
import br.com.chiken_pix_back.chikenpix.exception.StatusInvalidoException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Transacao {
    private final String id;
    private final double valor;
    private StatusTransacao status;
    private final LocalDateTime dateHora;
    private final ContaBancaria origem;
    private final ContaBancaria destino;

    public Transacao(ContaBancaria origem, ContaBancaria destino, double valor){
        this.id = UUID.randomUUID().toString();
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
        this.status = StatusTransacao.PENDENTE;
        this.dateHora = LocalDateTime.now();
    }

    public void concluir(){
        this.status = StatusTransacao.CONCLUIDA;
    }

    public void cancelar(){
        if (this.status != StatusTransacao.PENDENTE){
            throw new StatusInvalidoException("Somente transações pendentes podem ser canceladas!");
        }

        this.status = StatusTransacao.CANCELADA;
    }



}
