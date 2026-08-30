package br.com.chiken_pix_back.chikenpix.exception;

public class NomeInvalidoException extends RuntimeException {
    public NomeInvalidoException(String message) {
        super(message);
    }
}
