package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.enumerations.TipoTransacao;
import br.com.chiken_pix_back.chikenpix.exception.*;
import br.com.chiken_pix_back.chikenpix.repository.ChavePixRepository;
import br.com.chiken_pix_back.chikenpix.repository.ContaBancariaRepository;
import br.com.chiken_pix_back.chikenpix.repository.TransacaoRepository;
import br.com.chiken_pix_back.chikenpix.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static br.com.chiken_pix_back.chikenpix.model.Usuario.validarCNPJ;
import static br.com.chiken_pix_back.chikenpix.model.Usuario.validarCPF;

@Component
public class Banco {

    private final UserRepository usuarios;
    private final ContaBancariaRepository contas;
    private final ChavePixRepository chaves;
    private final TransacaoRepository transacoes;

    public Banco(UserRepository usuarios,
                 ContaBancariaRepository contas,
                 ChavePixRepository chaves,
                 TransacaoRepository transacoes) {
        this.usuarios = usuarios;
        this.contas = contas;
        this.chaves = chaves;
        this.transacoes = transacoes;
    }

    public Usuario getUsuario(String id) {
        return usuarios.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Error: Usuario não encontrado"));
    }

    public void addUsuario(Usuario usuario) {
        usuarios.save(usuario);
    }

    public ContaBancaria buscarConta(String chave) {
        return chaves.findByChave(chave)
                .map(ChavePix::getContaBancaria)
                .orElse(null);
    }

    public void removerUsuario(String id) {
        if (!usuarios.existsById(id)) {
            throw new IdNaoEncontradoException("Error: Usuario não encontrado");
        }
        usuarios.deleteById(id);
    }

    public Usuario cadastrarUsuario(String nome, String email, String cpf, String senha, String cnpj, String telefone) {
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

        Usuario usuario = new Usuario(nome, email, cpfValidado, senha, cnpjValidado, telefone);
        addUsuario(usuario);
        return usuario;
    }

    public void realizarPix(ContaBancaria origem, String chaveDestino, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorPixInvalidoException("Error: Valor inválido para realizar Pix.");
        }

        ContaBancaria destino = buscarConta(chaveDestino);
        if (destino == null) {
            throw new ChaveNaoEncontradaException("Error: Chave Pix de destino não encontrada.");
        }

        origem.debitar(valor);
        destino.creditar(valor);

        contas.save(origem);
        contas.save(destino);

        Transacao transacao = new Transacao(origem, destino, valor, TipoTransacao.PIX_ENVIADO);
        transacao.concluir();
        transacoes.save(transacao);
    }

    public ContaBancaria consultarConta(String numeroConta) {
        return contas.findById(numeroConta)
                .orElseThrow(() -> new IdNaoEncontradoException("Error: Conta não encontrada"));
    }
}