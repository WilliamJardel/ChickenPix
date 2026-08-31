package br.com.chiken_pix_back.chikenpix.model;
import br.com.chiken_pix_back.chikenpix.exception.EmailInvalidoException;
import br.com.chiken_pix_back.chikenpix.exception.SenhaInvalidaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class UsuarioTest {
    @Test
    @DisplayName("Crie um usuário com ID e Conta")
    void crieUsuarioSucesso() {
        Usuario usuario = new Usuario("Nay Dantas", "nay@gmail.com", "123.45.234-78", "Nay123", null, "88999998888");

        assertThat(usuario.getId()).isNotNull();
        assertThat(usuario.getConta()).isNotNull();
       // assertThat(usuario.getConta().getId()).isNotNull();
        assertThat(usuario.getNome()).isEqualTo("Nay Dantas");
    }

    @Test
    @DisplayName("Deve lançar exceção ao validar senha fraca")
    void deveValidarSenhaFraca() {
        assertThatThrownBy(() -> Usuario.validarSenha("123456"))
                .isInstanceOf(SenhaInvalidaException.class)
                .hasMessageContaining("Error: Senha Inválida");
    }

    @Test
    @DisplayName("Deve validar senha forte")
    void deveValidarSenhaForte() {
        assertDoesNotThrow(() -> Usuario.validarSenha("Senha@1234"));
    }

    @Test
    @DisplayName("Deve lançar exceção para e-mail sem @")
    void deveLancarExcecaoParaEmailInvalido() {
        assertThatThrownBy(() -> Usuario.validarEmail("emailsemdominio.com"))
                .isInstanceOf(EmailInvalidoException.class);

    }

    @Test
    @DisplayName("Deve atualizar o nome com sucesso quando válido")
    void deveAtualizarNomeComSucesso() {
        Usuario usuario = new Usuario("Kethillyn", "keth@email.com", "123.456.789-00", "Senha@123", null, "88999998888");
        usuario.atualizarNome("Kethillyn Costa");

        assertThat(usuario.getNome()).isEqualTo("Kethillyn Costa");
    }


}
