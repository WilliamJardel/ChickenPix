package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.exception.IdNaoEncontradoException;
import br.com.chiken_pix_back.chikenpix.exception.EmailInvalidoException;
import br.com.chiken_pix_back.chikenpix.exception.NomeInvalidoException;
import br.com.chiken_pix_back.chikenpix.exception.CPFInvalidoException;
import br.com.chiken_pix_back.chikenpix.exception.SenhaInvalidaException;
import java.util.HashMap;

public class Banco {
    private HashMap<Integer, Usuario> usuarios;

    public Banco() {
        this.usuarios = new HashMap<Integer, Usuario>();
    }

    public Usuario getUsuario(int id) {
        return usuarios.get(id);
    }

    public void addUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

    public Usuario rmUsuario(int id) {
        return usuarios.remove(id);
    }

    public ContaBancaria buscarConta(String chave) {
        for (Usuario usuario: usuarios.values()) {
            ContaBancaria conta = usuario.getConta();

            for (TipoChavePix tipoChavePix : TipoChavePix.values()) {
                ChavePix chavePix = conta.buscarChavePix(tipoChavePix);

                if (chavePix != null && chavePix.getChave().equals(chave)) {
                    return conta;
                }
            }

        }
        return null;
    }

    public void RemoverUsuario(int id) throws IdNaoEncontradoException {
        Usuario usuarioRemovido = usuarios.remove(id);
        //como a função retorna o objeto usuario, então se o ID existia a condição == null vai ser false
        // e a exceção não vai ser lançada e o usuario vai ser removido com sucesso "espero",
        // mas se o ID não existe, a condição vai ser verdadeira e adaí lança
        // a exceção
        if(usuarioRemovido == null) {
            throw new IdNaoEncontradoException("Usuario não encontrado");
        }
    }

    public Usuario cadastrarUsuario(int id, String nome, String email, String cpf, String senha, String numeroConta) throws NomeInvalidoException, EmailInvalidoException, CPFInvalidoException, SenhaInvalidaException {
        //vamos usar as validacoes da classe usuario
        Usuario.validarNome(nome);
        Usuario.validarEmail(email);
        Usuario.validarCPF(cpf);
        Usuario.validarSenha(senha);

        ContaBancaria conta = new ContaBancaria(numeroConta);

        Usuario usuario = new Usuario(
                id,
                nome,
                email,
                cpf,
                senha,
                conta
        );

        addUsuario(usuario);

        return usuario;

    }

}
