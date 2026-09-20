package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.enumerations.TipoChavePix;
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

        contaDaniel.addChavePix(TipoChavePix.EMAIL, "daniel@gmail.com");

        ChavePix chaveEncontrada = contaDaniel.buscarChavePix(TipoChavePix.EMAIL);

        assertThat(chaveEncontrada).isNotNull();
        assertThat(chaveEncontrada.getChave()).isEqualTo("daniel@gmail.com");
        assertThat(chaveEncontrada.getTipoChave()).isEqualTo(TipoChavePix.EMAIL);
    }

    @Test
    @DisplayName("Deve gerar e cadastrar chave Pix aleatória na Conta Bancaria")
    void deveCadastrarChaveAleatoria(){
        ContaBancaria contaDaniel = new ContaBancaria("12345");

        ChavePix chaveGerada = contaDaniel.gerarChaveAleatoria();

        ChavePix chaveEncontrada = contaDaniel.buscarChavePix(TipoChavePix.ALEATORIA);

        assertThat(chaveEncontrada).isNotNull();
        assertThat(chaveEncontrada.getChave()).isEqualTo(chaveGerada.getChave());
        assertThat(chaveEncontrada.getTipoChave()).isEqualTo(TipoChavePix.ALEATORIA);
    }

    @Test
    void naoDevePermitirDuasChavesDoMesmoTipo() {

        ContaBancaria contaDaniel = new ContaBancaria("12345");

        contaDaniel.addChavePix(TipoChavePix.EMAIL, "daniel1@gmail.com");

        assertThatThrownBy(
                () -> contaDaniel.addChavePix(TipoChavePix.EMAIL, "daniel2@gmail.com")
        )
                .isInstanceOf(ChavePixJaCadastradaException.class)
                .hasMessageContaining("Error: Chave Pix já cadastrada.");
    }
}