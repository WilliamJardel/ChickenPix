package br.com.chiken_pix_back.chikenpix.exception;

public class ContaDesativadaException extends RuntimeException{
    public ContaDesativadaException(String msg){
        super(msg);
    }
}
