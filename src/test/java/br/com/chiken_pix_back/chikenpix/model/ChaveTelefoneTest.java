package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.exception.TelefoneInvalidoException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ChaveTelefoneTest {

    @Test
    @DisplayName("Deve criar uma chave Pix do tipo Telefone válida")
    void deveCriarChaveTelefoneValida() {

        ChaveTelefone chave = new ChaveTelefone("+5562987933305");

        assertThat(chave.getChave()).isEqualTo("+5562987933305");
        assertThat(chave.getTipoChave()).isEqualTo(TipoChavePix.TELEFONE);
        assertThat(chave.validar()).isTrue();
    }

    @Test
    @DisplayName("Deve lançar a exceção de TelefoneInvalido")
    void deveLancarExcecaoParaCTelefoneInvalido() {

        ChaveTelefone chave = new ChaveTelefone("+55629879333044");

        assertThatThrownBy(
                chave::validar
        )
                .isInstanceOf(TelefoneInvalidoException.class)
                .hasMessageContaining(
                        "Telefone inválido, o formato aceito é +55DD9XXXXXXXX"
                );
    }

    @Test
    @DisplayName("Deve lançar a exceção de TelefoneInvalido por estar vazio")
    void deveLancarExcecaoParaTelefoneVazio() {

        ChaveTelefone chave = new ChaveTelefone("");

        assertThatThrownBy(
                chave::validar
        )
                .isInstanceOf(TelefoneInvalidoException.class)
                .hasMessageContaining("O telefone não pode ser vazio");
    }
}
