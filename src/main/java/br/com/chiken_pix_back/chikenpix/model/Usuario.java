package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.exception.EmailInvalidoException;
import br.com.chiken_pix_back.chikenpix.exception.NomeInvalidoException;
import br.com.chiken_pix_back.chikenpix.exception.SenhaInvalidaException;

import lombok.Getter;
import lombok.Setter;

public class Usuario {
    @Getter @Setter private int id;
    @Getter @Setter private String nome;
    @Getter @Setter private String email;
    @Getter @Setter private String cpf;
    @Getter @Setter private String senha;
    private @Getter final ContaBancaria conta;

    public Usuario(int id, String nome, String email, String cpf, String senha, ContaBancaria conta){

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.conta = conta;

    }


    public static void validarSenha(String valor) throws SenhaInvalidaException {
        if (valor == null || valor.isBlank() || !valor.trim().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{8,}$")){
                throw new SenhaInvalidaException("Senha Inválida");
        }
        //Nesse metodo primeiro verifica se a senha não está nula ou se o espaço está vazio ou cheio de espaços em branco,
        //depois eu uso a trim() pra retirar qualquer espaço que o usuário digite "sem querer" e uso p matches() para estabelecer o padrão da ssenha a ser definida
        // que é ter pelo menos uma legtra maiuscula, uma minuscula, um numero e um caractere especial e deve ter no minimo 8 caractes.
    }
    public static void validarEmail(String email) throws EmailInvalidoException {
        if(email == null || email.isBlank() || !email.trim().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new EmailInvalidoException("email Inválido");
        }
    }

    public static void validarNome(String nome) throws NomeInvalidoException{
        if(nome == null || nome.isBlank() || !nome.trim().matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\\\s]{3,100}$")) {
            throw new NomeInvalidoException("Nome Inválido");
        }
    }
}
