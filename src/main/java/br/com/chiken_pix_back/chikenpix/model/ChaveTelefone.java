package br.com.chiken_pix_back.chikenpix.model;
import br.com.chiken_pix_back.chikenpix.exception.TelefoneInvalidoException;

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
        if (this.chave == null || this.chave.isBlank()) {
            throw new TelefoneInvalidoException("O telefone não pode ser vazio");
        }
        String regexTelefone = "^\\+55[1-9]{2}9\\d{8}$";
        if (!this.chave.matches(regexTelefone)) {
            throw new TelefoneInvalidoException("Telefone inválido, o formato aceito é +55DD9XXXXXXXX");
        }
        return true;
    }

}
