package br.com.chiken_pix_back.chikenpix.exception;

public class ChaveNaoEncontradaException extends RuntimeException{
    public ChaveNaoEncontradaException(String msg){
        super(msg);
    }
}
