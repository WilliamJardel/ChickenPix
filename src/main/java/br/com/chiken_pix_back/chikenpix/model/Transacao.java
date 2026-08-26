package br.com.chiken_pix_back.chikenpix.model;
import java.time.LocalDateTime;

public class Transacao {
    private String id;
    private double valor;
    private TipoTransacao tipo;
    private static LocalDateTime dateTime;
    private ContaBancaria origem;
    private ContaBancaria destino;

    public Transacao(double valor, ContaBancaria destino){
        this.valor = valor;
        this.destino = destino;
    }


}
