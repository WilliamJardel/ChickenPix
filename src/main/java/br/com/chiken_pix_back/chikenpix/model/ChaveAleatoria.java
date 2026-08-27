package br.com.chiken_pix_back.chikenpix.model;

import java.util.UUID;

public class ChaveAleatoria implements ChavePix{
    private String chave;

    public ChaveAleatoria(){
    }

    @Override
    public String getChave() {
        return this.chave;
    }

    @Override
    public TipoChavePix getTipoChave() {
        return TipoChavePix.ALEATORIA;
    }
}
