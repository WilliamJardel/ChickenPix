package br.com.chiken_pix_back.chikenpix.exception;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException(String msg){
        super(msg);
    }
}
