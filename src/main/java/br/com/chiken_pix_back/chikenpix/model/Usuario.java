package br.com.chiken_pix_back.chikenpix.model;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private ContaBancaria conta;

    public Usuario(String nome, String email, String cpf, String senha, ContaBancaria conta) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.conta = conta;

        //quando criar o pacote de exceções colocar uma pra senha passada não atender aos critérios
    }

    public boolean validarSenha(String valor) {
        if(this.senha ==  null || this.senha.isBlank()) {
            return false;
        }

        return this.senha.trim().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{8,}$");

        //Nesse metodo primeiro verifica se a senha não está nula ou se o espaço está vazio ou cheio de espaços em branco,
        //depois eu uso a trim() pra retirar qualquer espaço que o usuário digite "sem querer" e uso p matches() para estabelecer o padrão da ssenha a ser definida
        // que é ter pelo menos uma legtra maiuscula, uma minuscula, um numero e um caractere especial e deve ter no minimo 8 caractes.
    }
    public void atualizarSenha(String valor) {
        //if(!validarSenha(valor)){
            // lançar uma exceção
        //}
        this.senha = senha;
    }

}
