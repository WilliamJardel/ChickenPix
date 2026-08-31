package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.exception.CPFInvalidoException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ChaveCpfTest {

    @Test
    @DisplayName("Deve gerar uma chave Pix do tipo CPF válida")
    void deveCriarChaveCPFValida() {

        ChaveCpf chave = new ChaveCpf("071.894.067-06");

        assertThat(chave.getChave()).isEqualTo("071.894.067-06");
        assertThat(chave.getTipoChave()).isEqualTo(TipoChavePix.CPF);
        assertThat(chave.validar()).isTrue();
    }

    @Test
    @DisplayName("Deve lançar a exceção de CPFInvalido")
    void deveLancarExcecaoParaCPFInvalido() {

        ChaveCpf chave = new ChaveCpf("07183406706");

        assertThatThrownBy(
                chave::validar
        )
                .isInstanceOf(CPFInvalidoException.class)
                .hasMessageContaining(
                        "CPF inválido"
                );
    }

    @Test
    @DisplayName("Deve lançar a exceção de CPFInvalido por estar vazio")
    void deveLancarExcecaoParaCPFVazio() {

        ChaveCpf chave = new ChaveCpf("");

        assertThatThrownBy(
                chave::validar
        )
                .isInstanceOf(CPFInvalidoException.class)
                .hasMessageContaining("O CPF não pode ser vazio");
    }
}