package br.com.chiken_pix_back.chikenpix.model;

import br.com.caelum.stella.tinytype.CPF;
import br.com.chiken_pix_back.chikenpix.exception.*;

import lombok.Getter;
import lombok.Setter;

public class Usuario {
    private @Getter int id;
    @Getter @Setter private String nome;
    @Getter @Setter private String email;
    @Getter @Setter private String cpf;
    @Getter @Setter private String senha;
    private @Getter final ContaBancaria conta;
    @Getter @Setter private String cnpj;

    public Usuario(int id, String nome, String email, String cpf, String senha, ContaBancaria conta, String cnpj){

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.conta = conta;
        this.cnpj = cnpj;

    }


    public static void validarSenha(String valor) {
        if (valor == null || valor.isBlank() || !valor.trim().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{8,}$")){
                throw new SenhaInvalidaException("Error: Senha Inválida");
        }
        //Nesse metodo primeiro verifica se a senha não está nula ou se o espaço está vazio ou cheio de espaços em branco,
        //depois eu uso a trim() pra retirar qualquer espaço que o usuário digite "sem querer" e uso p matches() para estabelecer o padrão da ssenha a ser definida
        // que é ter pelo menos uma legtra maiuscula, uma minuscula, um numero e um caractere especial e deve ter no minimo 8 caractes.
    }
    public static void validarEmail(String email) {
        if(email == null || email.isBlank() || !email.trim().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new EmailInvalidoException("Error: Email Inválido");
        }
    }

    public static void validarNome(String nome) {
        if(nome == null || nome.isBlank() || !nome.trim().matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\\\s]{3,100}$")) {
            throw new NomeInvalidoException("Error: Nome Inválido");
        }
    }

    public static void validarCPF(String cpf) {
        if(cpf == null || !cpf.trim().matches("^(\\\\d{11}|\\\\d{3}\\\\.\\\\d{3}\\\\.\\\\d{3}-\\\\d{2})$")) {
            throw new CPFInvalidoException("Error: CPF Inválido");
        }

    }



    public static void validarCNPJ(String cnpj) throws CNPJInvalidoException {
        if (cnpj == null || cnpj.isBlank() || !cnpj.trim().matches("^(\\d{14}|\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2})$")) {
            throw new CNPJInvalidoException("Error: CNPJ Inválido");
        }
    }


    public void atualizarNome(String novoNome) throws NomeInvalidoException{
        if(novoNome != null && !novoNome.isBlank()) {
            validarNome(novoNome);
            this.nome = novoNome;
        }


    }

    public void atualizarEmail(String novoEmail) throws EmailInvalidoException{
        if(novoEmail != null && !novoEmail.isBlank()) {
            validarEmail(novoEmail);
            this.email = novoEmail;
        }
    }

    public void atualizarSenha(String novaSenha) throws  SenhaInvalidaException{
        if(novaSenha != null && !novaSenha.isBlank()){
            validarSenha(novaSenha);
            this.senha = novaSenha;
        }
    }
}
