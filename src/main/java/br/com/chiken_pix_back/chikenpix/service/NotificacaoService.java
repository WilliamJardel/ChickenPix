package br.com.chiken_pix_back.chikenpix.service;

import br.com.chiken_pix_back.chikenpix.model.Banco;
import br.com.chiken_pix_back.chikenpix.model.Transacao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacaoService {
    private Banco banco;

    public NotificacaoService(Banco banco) {
        this.banco = banco;
    }

    public List<Transacao> listarNotificacoes(String numeroConta) {
        return banco.consultarNotificacoes(numeroConta);
    }
}