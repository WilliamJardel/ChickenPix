package br.com.chiken_pix_back.chikenpix.model;

public class ChaveEmail implements ChavePix {
    private String chave;

    public ChaveEmail() {}

    public ChaveEmail(String chave) {
        this.chave = chave;
    }

    @Override
    public String getChave(){
        return this.chave;
    }

    @Override
    public TipoChavePix getTipoChave(){
        return TipoChavePix.EMAIL;
    }

    @Override
    public boolean validar(){
        return validaEmail();
    }

    private boolean validaEmail(){
        if (this.chave == null || this.chave.isBlank()) {
            return false;
        }
        return this.chave.trim().matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

}
