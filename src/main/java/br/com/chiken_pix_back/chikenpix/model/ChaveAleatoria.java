package br.com.chiken_pix_back.chikenpix.model;

import java.util.UUID;

public class ChaveAleatoria implements ChavePix{
    private String chave;

    public ChaveAleatoria(){
        this.chave = UUID.randomUUID().toString();
    }

    @Override
    public String getChave() {
        return this.chave;
    }

    @Override
    public TipoChavePix getTipoChave() {
        return TipoChavePix.ALEATORIA;
    }

    @Override
    public boolean validar() {
        return validarUUID();
    }

    private boolean validarUUID(){
        try {
            UUID.fromString(chave);
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }
}
