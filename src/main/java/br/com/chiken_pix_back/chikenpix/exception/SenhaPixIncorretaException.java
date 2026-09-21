package br.com.chiken_pix_back.chikenpix.exception;

public class SenhaPixIncorretaException extends RuntimeException {
    public SenhaPixIncorretaException(String message) {
        super(message);
    }
}
