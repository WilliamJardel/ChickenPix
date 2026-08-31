package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.exception.ChavePixJaCadastradaException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


public class ContaBancariaTest {

    @Test
    @DisplayName("Deve cadastrar Chave Pix do tipo Email na Conta Bancaria")
    void deveCadastrarChaveEmail(){
        ContaBancaria contaDaniel = new ContaBancaria("12345");
        ChaveEmail chave = new ChaveEmail("daniel@gmail.com");

        contaDaniel.addChavePix(TipoChavePix.EMAIL, chave);

        ChavePix chaveEncontrada = contaDaniel.buscarChavePix(TipoChavePix.EMAIL);

        assertThat(chaveEncontrada).isNotNull();
        assertThat(chaveEncontrada.getChave()).isEqualTo("daniel@gmail.com");
        assertThat(chaveEncontrada.getTipoChave()).isEqualTo(TipoChavePix.EMAIL);

    }

    @Test
    @DisplayName("Deve gerar e cadastrar chave Pix aleatória na Conta Bancaria")
    void deveCadastrarChaveAleatoria(){
        ContaBancaria contaDaniel = new ContaBancaria("12345");
        ChaveAleatoria chave = new ChaveAleatoria();

        contaDaniel.addChavePix(TipoChavePix.ALEATORIA, chave);

        ChavePix chaveEncontrada = contaDaniel.buscarChavePix(TipoChavePix.ALEATORIA);

        assertThat(chaveEncontrada).isNotNull();
        assertThat(chaveEncontrada.getChave()).isEqualTo(chave.getChave());
        assertThat(chaveEncontrada.getTipoChave()).isEqualTo(TipoChavePix.ALEATORIA);
    }

    @Test
    void naoDevePermitirDuasChavesDoMesmoTipo() {

        ContaBancaria contaDaniel = new ContaBancaria("12345");

        ChaveEmail primeiraChave =
                new ChaveEmail("daniel1@gmail.com");

        ChaveEmail segundaChave =
                new ChaveEmail("daniel2@gmail.com");

        contaDaniel.addChavePix(TipoChavePix.EMAIL, primeiraChave);

        assertThatThrownBy(
                () -> contaDaniel.addChavePix(TipoChavePix.EMAIL, segundaChave)
        )
                .isInstanceOf(ChavePixJaCadastradaException.class)
                .hasMessageContaining("Error: Chave Pix já cadastrada.");
    }

    @Test
    @DisplayName("Deve inicializar a conta com todos os valores padrão e saldo zerado")
    void deveInicializarContaComValoresPadrao() {
        ContaBancaria conta = new ContaBancaria("12345");

        assertThat(conta.getNumeroConta()).isEqualTo("12345");
        assertThat(conta.getNumeroAgencia()).isEqualTo("0001001");
        assertThat(conta.getCodigoBanco()).isEqualTo("D022NSJZ02012");
        assertThat(conta.getNomeBanco()).isEqualTo("ChikenPIX");
        assertThat(conta.getSaldo()).isEqualTo(0.00);
        assertThat(conta.getStatus()).isEqualTo(StatusConta.ATIVA);
    }
}
