package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.exception.CNPJInvalidoException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ChaveCnpjTest {

    @Test
    @DisplayName("Deve gerar uma chave Pix do tipo CNPJ válida")
    void deveCriarChaveCNPJValida() {

        ChaveCnpj chave = new ChaveCnpj("11222333000181");

        assertThat(chave.getChave()).isEqualTo("11222333000181");
        assertThat(chave.getTipoChave()).isEqualTo(TipoChavePix.CNPJ);
        assertThat(chave.validar()).isTrue();
    }

    @Test
    @DisplayName("Deve lançar a exceção de CPNJInvalido")
    void deveLancarExcecaoParaCNPJInvalido() {

        ChaveCnpj chave = new ChaveCnpj("0000000000191");

        assertThatThrownBy(
                chave::validar
        )
                .isInstanceOf(CNPJInvalidoException.class)
                .hasMessageContaining(
                        "CNPJ inválido"
                );
    }

    @Test
    @DisplayName("Deve lançar a exceção de CPNJInvalido por estar vazio")
    void deveLancarExcecaoParaCNPJVazio() {

        ChaveCnpj chave = new ChaveCnpj("");

        assertThatThrownBy(
                chave::validar
        )
                .isInstanceOf(CNPJInvalidoException.class)
                .hasMessageContaining("O CNPJ não pode ser vazio");
    }
}