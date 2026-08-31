package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.exception.ChavePixJaCadastradaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class ContaBancariaTest {

    @Test
    void deveCadastrarChaveEmail(){
        ContaBancaria contaDaniel = new ContaBancaria("12345");
        ChaveEmail chave = new ChaveEmail("daniel@gmail.com");

        contaDaniel.addChavePix(TipoChavePix.EMAIL, chave);

        ChavePix chaveEncontrada = contaDaniel.buscarChavePix(TipoChavePix.EMAIL);

        assertNotNull(chaveEncontrada);
        assertEquals("daniel@gmail.com", chaveEncontrada.getChave());
        assertEquals(TipoChavePix.EMAIL, chaveEncontrada.getTipoChave());
    }

    @Test
    void deveCadastrarChaveAleatoria(){
        ContaBancaria contaDaniel = new ContaBancaria("12345");
        ChaveAleatoria chave = new ChaveAleatoria();

        contaDaniel.addChavePix(TipoChavePix.ALEATORIA, chave);

        ChavePix chaveEncontrada = contaDaniel.buscarChavePix(TipoChavePix.ALEATORIA);

        assertNotNull(chaveEncontrada);
        assertEquals(chave.getChave(), chaveEncontrada.getChave());
        assertEquals(TipoChavePix.ALEATORIA, chaveEncontrada.getTipoChave());
    }

    @Test
    void naoDevePermitirDuasChavesDoMesmoTipo() {

        ContaBancaria contaDaniel = new ContaBancaria("12345");

        ChaveEmail primeiraChave =
                new ChaveEmail("daniel1@gmail.com");

        ChaveEmail segundaChave =
                new ChaveEmail("daniel2@gmail.com");

        contaDaniel.addChavePix(TipoChavePix.EMAIL, primeiraChave);

        assertThrows(
                ChavePixJaCadastradaException.class,
                () -> contaDaniel.addChavePix(TipoChavePix.EMAIL, segundaChave)
                // Depois de executar o assertThrows ele esxecuta o addChave
                // Caso não tivesse o '() ->' o java esxutaria primeiro o add e depois o assert.
        );
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
