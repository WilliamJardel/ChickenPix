package br.com.chiken_pix_back.chikenpix.model;
import br.com.chiken_pix_back.chikenpix.exception.CpfInvalidoException;

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

    public boolean validaCpf(){

        if (this.chave == null || this.chave.isBlank()){
            throw new CpfInvalidoException("O cpf não pode ser vazio");
        }
        String regexCPF = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";

        if (!this.chave.matches(regexCPF)){
            throw new CpfInvalidoException("Cpf inválido, o formato é 000.000.000-00");
        }
        return true;
    }

}
