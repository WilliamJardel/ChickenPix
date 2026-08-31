package br.com.chiken_pix_back.chikenpix.service;

import br.com.chiken_pix_back.chikenpix.exception.ChaveNaoEncontradaException;
import br.com.chiken_pix_back.chikenpix.exception.ValorPixInvalidoException;
import br.com.chiken_pix_back.chikenpix.model.ChaveEmail;
import br.com.chiken_pix_back.chikenpix.model.Banco;
import br.com.chiken_pix_back.chikenpix.model.TipoChavePix;
import br.com.chiken_pix_back.chikenpix.model.Usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PixServiceTest {

    private Banco banco;
    private PixService pixService;

    @BeforeEach
    void setUp() {
        banco = new Banco();
        pixService = new PixService(banco);
    }

    @Test
    @DisplayName("Deve realizar um Pix para outra conta com sucesso")
    void deveRealizarPixComSucesso(){
        Usuario usuarioAutenticadoOrigem = new Usuario(
                "Regis",
                "regis@email.com",
                "894.321.242-06",
                "bancodedados",
                null,
                "82976099776"
        );
        usuarioAutenticadoOrigem.getConta().creditar(100);


        Usuario usuarioAutenticadoDestino = new Usuario(
                "Camila",
                "camila@email.com",
                "124.324.244-07",
                "minecraft",
                null,
                "82976089345"
        );

        usuarioAutenticadoDestino.getConta()
                .addChavePix(TipoChavePix.EMAIL, new ChaveEmail("camila@email.com"));

        banco.addUsuario(usuarioAutenticadoDestino);

        pixService.realizarPix(
                usuarioAutenticadoOrigem.getConta(),
                "camila@email.com",
                40
        );

        assertThat(usuarioAutenticadoDestino.getConta().getSaldo()).isEqualTo(40);
        assertThat(usuarioAutenticadoOrigem.getConta().getSaldo()).isEqualTo(60);
    }

    @Test
    @DisplayName("Não deve realiar pix com valor inválido")
    void naoDeveRealizarPixInvalido(){
        Usuario usuarioAutenticadoOrigem = new Usuario(
                "Regis",
                "regis@email.com",
                "894.321.242-06",
                "bancodedados",
                null,
                "82976099776"
        );
        usuarioAutenticadoOrigem.getConta().creditar(100);


        Usuario usuarioAutenticadoDestino = new Usuario(
                "Camila",
                "camila@email.com",
                "124.324.244-07",
                "minecraft",
                null,
                "82976089345"
        );

        usuarioAutenticadoDestino.getConta()
                .addChavePix(TipoChavePix.EMAIL, new ChaveEmail("camila@email.com"));

        banco.addUsuario(usuarioAutenticadoDestino);

        assertThatThrownBy(
                () -> pixService.realizarPix(
                        usuarioAutenticadoOrigem.getConta(),
                        "camila@email.com",
                        -40
                )
        )
                .isInstanceOf(ValorPixInvalidoException.class)
                .hasMessage("Error: Valor inválido para realizar Pix.");


    }

    @Test
    @DisplayName("Não deve realiar pix, se a chave pix não for encontrada")
    void naoDeveRealizarPixComChaveNaoEcontrada(){
        Usuario usuarioAutenticadoOrigem = new Usuario(
                "Regis",
                "regis@email.com",
                "894.321.242-06",
                "bancodedados",
                null,
                "82976099776"
        );
        usuarioAutenticadoOrigem.getConta().creditar(100);


        Usuario usuarioAutenticadoDestino = new Usuario(
                "Camila",
                "camila@email.com",
                "124.324.244-07",
                "minecraft",
                null,
                "82976089345"
        );


        banco.addUsuario(usuarioAutenticadoDestino);

        assertThatThrownBy(
                () -> pixService.realizarPix(
                        usuarioAutenticadoOrigem.getConta(),
                        "camila@email.com",
                        40
                )
        )
                .isInstanceOf(ChaveNaoEncontradaException.class)
                .hasMessage("Error: Chave Pix não encontrada.");


    }
}
