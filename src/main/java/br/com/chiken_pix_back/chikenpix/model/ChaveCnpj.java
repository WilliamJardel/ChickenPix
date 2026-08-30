package br.com.chiken_pix_back.chikenpix.model;
import br.com.caelum.stella.validation.CNPJValidator;
import br.com.chiken_pix_back.chikenpix.exception.CNPJInvalidoException;

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
        if (this.chave == null || this.chave.isBlank()) {
            throw new CNPJInvalidoException("O CNPJ não pode ser vazio");
        }
        boolean ehValido = new CNPJValidator().isEligible(this.chave);
        if (!ehValido) {
            throw new CNPJInvalidoException("CNPJ inválido");
        }
        return true;
    }


}
