package br.com.chiken_pix_back.chikenpix.model;

public interface ChavePix {

    String getChave();

    TipoChavePix getTipoChave();

    boolean validar();

}
