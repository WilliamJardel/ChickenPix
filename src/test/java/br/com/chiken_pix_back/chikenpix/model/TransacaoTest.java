package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.enumerations.StatusTransacao;
import br.com.chiken_pix_back.chikenpix.exception.StatusInvalidoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class TransacaoTest {
    @Test
    @DisplayName("Deve criar uma transação com status pendente")
    void deveCriarTransacaoComStatusPendente() {

        ContaBancaria origem = new ContaBancaria("conta-origem");
        ContaBancaria destino = new ContaBancaria("conta-destino");

        Transacao transacao = new Transacao(
                origem,
                destino,
                100.00
        );

        assertThat(transacao).isNotNull();
        assertThat(transacao.getId()).isNotNull();
        assertThat(transacao.getOrigem()).isEqualTo(origem);
        assertThat(transacao.getDestino()).isEqualTo(destino);
        assertThat(transacao.getValor()).isEqualTo(100.00);
        assertThat(transacao.getDateHora()).isNotNull();
        assertThat(transacao.getStatus())
                .isEqualTo(StatusTransacao.PENDENTE);
    }
    @Test
    @DisplayName("Deve concluir uma transação pendente")
    void deveConcluirTransacao() {

        ContaBancaria origem = new ContaBancaria("conta-origem");
        ContaBancaria destino = new ContaBancaria("conta-destino");

        Transacao transacao = new Transacao(
                origem,
                destino,
                100.00
        );

        transacao.concluir();

        assertThat(transacao.getStatus())
                .isEqualTo(StatusTransacao.CONCLUIDA);
    }

    @Test
    @DisplayName("Deve cancelar uma transação pendente")
    void deveCancelarTransacaoPendente() {

        ContaBancaria origem = new ContaBancaria("conta-origem");
        ContaBancaria destino = new ContaBancaria("conta-destino");

        Transacao transacao = new Transacao(
                origem,
                destino,
                100.00
        );

        transacao.cancelar();

        assertThat(transacao.getStatus())
                .isEqualTo(StatusTransacao.CANCELADA);
    }

    @Test
    @DisplayName("Não deve cancelar uma transação concluída")
    void naoDeveCancelarTransacaoConcluida() {

        ContaBancaria origem = new ContaBancaria("conta-origem");
        ContaBancaria destino = new ContaBancaria("conta-destino");

        Transacao transacao = new Transacao(
                origem,
                destino,
                100.00
        );

        transacao.concluir();

        assertThatThrownBy(() -> transacao.cancelar())
                .isInstanceOf(StatusInvalidoException.class)
                .hasMessageContaining(
                        "Somente transações pendentes podem ser canceladas!"
                );
    }
}
