package br.com.chiken_pix_back.chikenpix.exception;

public class CpfInvalidoException extends RuntimeException {
    public CpfInvalidoException(String mensagem) {
        super(mensagem);
    }
}
