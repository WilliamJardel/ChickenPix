package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.exception.EmailInvalidoException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ChaveEmailTest {

    @Test
    @DisplayName("Deve criar uma chave Pix do tipo Email válida")
    void deveCriarChaveEmailValida() {

        ChaveEmail chave = new ChaveEmail("es@gmail.com");

        assertThat(chave.getChave()).isEqualTo("es@gmail.com");
        assertThat(chave.getTipoChave()).isEqualTo(TipoChavePix.EMAIL);
        assertThat(chave.validar()).isTrue();
    }

    @Test
    @DisplayName("Deve lançar a exceção de EmailInvalido")
    void deveLancarExcecaoParaEmailInvalido() {

        ChaveEmail chave = new ChaveEmail("esgmail.com");

        assertThatThrownBy(
                chave::validar
        )
                .isInstanceOf(EmailInvalidoException.class)
                .hasMessageContaining(
                        "E-mail inválido, insira um formato correto (ex: usuario@dominio.com)"
                );
    }

    @Test
    @DisplayName("Deve lançar a exceção de EmailInvalido por estar vazio")
    void deveLancarExcecaoParaEmailVazio() {

        ChaveEmail chave = new ChaveEmail("");

        assertThatThrownBy(
                chave::validar
        )
                .isInstanceOf(EmailInvalidoException.class)
                .hasMessageContaining("O email não pode ser vazio");
    }
}