package br.com.chiken_pix_back.chikenpix.service;

import br.com.chiken_pix_back.chikenpix.exception.ContaBloqueadaSuspeitaFraudeException;
import br.com.chiken_pix_back.chikenpix.exception.SenhaPixIncorretaException;
import br.com.chiken_pix_back.chikenpix.model.Banco;
import br.com.chiken_pix_back.chikenpix.model.ContaBancaria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PixService {
    private Banco banco;
    private final Map<String, Integer> tentativasSenha = new ConcurrentHashMap<>();

    public PixService(Banco banco){
        this.banco = banco;
    }

    public void realizarPix(ContaBancaria origem, String chaveDestino, BigDecimal valor, String senha) {

        validarSenha(origem, senha);



        tentativasSenha.remove(origem.getNumeroConta());
    }

    private void validarSenha(ContaBancaria conta, String senhaDigitada) {
        String senhaCorreta = conta.getUsuario().getSenha();

        if (senhaDigitada == null || !senhaDigitada.equals(senhaCorreta)) {
            String numeroConta = conta.getNumeroConta();
            int erros = tentativasSenha.getOrDefault(numeroConta, 0) + 1;

            if (erros >= 3) {
                tentativasSenha.remove(numeroConta);
                banco.bloquearContaPorFraude(numeroConta);
                throw new ContaBloqueadaSuspeitaFraudeException(
                        "Error: Conta bloqueada por suspeita de fraude devido a 3 tentativas de senha incorreta."
                );
            }

            tentativasSenha.put(numeroConta, erros);
            throw new SenhaPixIncorretaException("Error: Senha incorreta. Tentativa " + erros + " de 3.");
        }
    }
}