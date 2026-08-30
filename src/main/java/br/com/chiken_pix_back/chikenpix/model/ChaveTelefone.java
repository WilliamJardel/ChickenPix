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

    @Override
    public TipoChavePix getTipoChave(){
        return TipoChavePix.TELEFONE;
    }

    @Override
    public boolean validar() {
        return validaTelefone();
    }

    private boolean validaTelefone() {
        String regexTelefone = "^\\+55[1-9]{2}9\\d{8}$";
        return this.chave != null && this.chave.matches(regexTelefone);
    }

}
