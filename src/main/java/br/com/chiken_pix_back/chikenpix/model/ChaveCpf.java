package br.com.chiken_pix_back.chikenpix.model;

public class ChaveCpf implements ChavePix {
    private String chave;

    public ChaveCpf() {}

    public ChaveCpf(String chave) {
        this.chave = chave;
    }

    @Override
    public String getChave(){
        return this.chave;
    }

    @Override
    public TipoChavePix getTipoChave(){
        return TipoChavePix.CPF;
    }

    @Override
    public boolean validar(){
        return validaCpf();
    }

    private boolean validaCpf(){
        String regexCPF = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
        return this.chave != null && this.chave.matches(regexCPF);
    }

}
