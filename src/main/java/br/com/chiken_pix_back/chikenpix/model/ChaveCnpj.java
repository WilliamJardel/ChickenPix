package br.com.chiken_pix_back.chikenpix.model;
import br.com.caelum.stella.validation.CNPJValidator;


public class ChaveCnpj implements ChavePix {
    private String chave;

    public ChaveCnpj() {}

    public ChaveCnpj(String chave) {
        this.chave = chave;
    }

    @Override
    public String getChave(){
        return this.chave;
    }

    @Override
    public TipoChavePix getTipoChave(){
        return TipoChavePix.CNPJ;
    }

    @Override
    public boolean validar(){
        if (this.chave == null) {
        return false;
    }
        return new CNPJValidator().isEligible(this.chave);
    }


}
