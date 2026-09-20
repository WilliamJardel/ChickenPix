package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.enumerations.TipoChavePix;
import br.com.chiken_pix_back.chikenpix.exception.IdNaoEncontradoException;
import br.com.chiken_pix_back.chikenpix.exception.CPFInvalidoException;
import org.springframework.stereotype.Component;

import static br.com.chiken_pix_back.chikenpix.model.Usuario.validarCNPJ;
import static br.com.chiken_pix_back.chikenpix.model.Usuario.validarCPF;

@Component
public class Banco {

    private final UserRepository usuarios;

    public Banco(UserRepository usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario(String id) {
        return usuarios.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Error: Usuario não encontrado"));
    }

    public void addUsuario(Usuario usuario) {
        usuarios.save(usuario);
    }

    public ContaBancaria buscarConta(String chave) {
        for (Usuario usuario : usuarios.findAll()) {
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

    public void removerUsuario(String id) {
        if (!usuarios.existsById(id)) {
            throw new IdNaoEncontradoException("Error: Usuario não encontrado");
        }
        usuarios.deleteById(id);
    }

    public Usuario cadastrarUsuario(String nome, String email, String cpf, String senha, String cnpj, String telefone) {
        //vamos usar as validacoes da classe usuario
        Usuario.validarNome(nome);
        Usuario.validarEmail(email);
        Usuario.validarSenha(senha);

        String cpfValidado = null;
        String cnpjValidado = null;

        if (cpf != null && !cpf.isBlank() && cnpj != null && !cnpj.isBlank()) {
            throw new CPFInvalidoException("Erro: Você não pode preencher CPF e CNPJ ao mesmo tempo.");
        }

        if ((cpf == null || cpf.isBlank()) && (cnpj == null || cnpj.isBlank())) {
            throw new CPFInvalidoException("Erro: É necessário informar o CPF ou o CNPJ.");
        }

        if (cpf != null && !cpf.isBlank()) {
            validarCPF(cpf);
            cpfValidado = cpf.trim();
        } else {
            validarCNPJ(cnpj);
            cnpjValidado = cnpj.trim();
        }

        Usuario usuario = new Usuario(
                nome,
                email,
                cpfValidado,
                senha,
                cnpjValidado,
                telefone
        );

        addUsuario(usuario);

        return usuario;

    }

}
