package br.com.chiken_pix_back.chikenpix.model;

public class ChaveTelefone implements ChavePix {
    private String chave;

    public ChaveTelefone() {}

    public ChaveTelefone(String chave){
        this.chave=chave;
    }

    @Override
    public String getChave() {
        return chave;
    }
}
