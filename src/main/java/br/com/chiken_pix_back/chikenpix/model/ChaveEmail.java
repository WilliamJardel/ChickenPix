package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.exception.EmailInvalidoException;

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
            throw new EmailInvalidoException("O email não pode ser vazio");
        }
        String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!this.chave.trim().matches(regexEmail)) {
            throw new EmailInvalidoException("E-mail inválido, insira um formato correto (ex: usuario@dominio.com)");
        }
        return true;
    }

}
