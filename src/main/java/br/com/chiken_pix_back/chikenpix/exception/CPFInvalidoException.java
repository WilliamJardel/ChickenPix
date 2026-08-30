package br.com.chiken_pix_back.chikenpix.exception;

public class CPFInvalidoException extends RuntimeException {
    public CPFInvalidoException(String message) {
        super(message);
    }
}