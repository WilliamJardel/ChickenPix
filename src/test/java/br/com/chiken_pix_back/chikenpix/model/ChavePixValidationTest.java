package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.enumerations.TipoChavePix;
import br.com.chiken_pix_back.chikenpix.exception.CNPJInvalidoException;
import br.com.chiken_pix_back.chikenpix.exception.CPFInvalidoException;
import br.com.chiken_pix_back.chikenpix.exception.EmailInvalidoException;
import br.com.chiken_pix_back.chikenpix.exception.TelefoneInvalidoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChavePixValidationTest {

    @Test
    @DisplayName("Deve validar CPF válido sem lançar exceção")
    void deveValidarCpfValido() {
        assertThatCode(() -> ChavePixValidation.validar(TipoChavePix.CPF, "123.456.789-00"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Deve lançar exceção para CPF inválido")
    void deveLancarExcecaoParaCpfInvalido() {
        assertThatThrownBy(() -> ChavePixValidation.validar(TipoChavePix.CPF, "abc"))
                .isInstanceOf(CPFInvalidoException.class);
    }

    @Test
    @DisplayName("Deve validar CNPJ válido sem lançar exceção")
    void deveValidarCnpjValido() {
        assertThatCode(() -> ChavePixValidation.validar(TipoChavePix.CNPJ, "12.345.678/0001-00"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Deve lançar exceção para CNPJ inválido")
    void deveLancarExcecaoParaCnpjInvalido() {
        assertThatThrownBy(() -> ChavePixValidation.validar(TipoChavePix.CNPJ, "abc"))
                .isInstanceOf(CNPJInvalidoException.class);
    }

    @Test
    @DisplayName("Deve validar e-mail válido sem lançar exceção")
    void deveValidarEmailValido() {
        assertThatCode(() -> ChavePixValidation.validar(TipoChavePix.EMAIL, "daniel@gmail.com"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Deve lançar exceção para e-mail inválido")
    void deveLancarExcecaoParaEmailInvalido() {
        assertThatThrownBy(() -> ChavePixValidation.validar(TipoChavePix.EMAIL, "email-invalido"))
                .isInstanceOf(EmailInvalidoException.class);
    }

    @Test
    @DisplayName("Deve validar telefone válido sem lançar exceção")
    void deveValidarTelefoneValido() {
        assertThatCode(() -> ChavePixValidation.validar(TipoChavePix.TELEFONE, "88999998888"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Deve lançar exceção para telefone inválido")
    void deveLancarExcecaoParaTelefoneInvalido() {
        assertThatThrownBy(() -> ChavePixValidation.validar(TipoChavePix.TELEFONE, "123"))
                .isInstanceOf(TelefoneInvalidoException.class);
    }

    @Test
    @DisplayName("Deve lançar exceção ao validar manualmente chave aleatória")
    void deveLancarExcecaoParaChaveAleatoria() {
        assertThatThrownBy(() -> ChavePixValidation.validar(TipoChavePix.ALEATORIA, "qualquer-valor"))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o valor for vazio")
    void deveLancarExcecaoParaValorVazio() {
        assertThatThrownBy(() -> ChavePixValidation.validar(TipoChavePix.EMAIL, ""))
                .isInstanceOf(IllegalArgumentException.class);
    }
}