package br.com.chiken_pix_back.chikenpix.exception;

public class StatusInvalidoException extends RuntimeException{
    public StatusInvalidoException(String msg){
        super(msg);
    }
}
