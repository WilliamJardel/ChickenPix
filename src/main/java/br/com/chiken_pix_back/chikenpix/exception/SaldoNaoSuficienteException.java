package br.com.chiken_pix_back.chikenpix.exception;

public class SaldoNaoSuficienteException extends RuntimeException {
    public SaldoNaoSuficienteException(String message) {
        super(message);
    }
}
