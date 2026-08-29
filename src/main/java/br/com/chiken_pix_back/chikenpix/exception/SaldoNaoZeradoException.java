package br.com.chiken_pix_back.chikenpix.exception;

public class SaldoNaoZeradoException extends RuntimeException {
    public SaldoNaoZeradoException(String message) {
        super(message);
    }
}
