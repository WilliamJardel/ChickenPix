package br.com.chiken_pix_back.chikenpix.service;

import br.com.chiken_pix_back.chikenpix.enumerations.TipoChavePix;
import br.com.chiken_pix_back.chikenpix.exception.ChaveNaoEncontradaException;
import br.com.chiken_pix_back.chikenpix.exception.SaldoInsuficienteException;
import br.com.chiken_pix_back.chikenpix.exception.ValorPixInvalidoException;
import br.com.chiken_pix_back.chikenpix.model.Banco;
import br.com.chiken_pix_back.chikenpix.model.ChavePix;
import br.com.chiken_pix_back.chikenpix.model.Usuario;
import br.com.chiken_pix_back.chikenpix.repository.ChavePixRepository;
import br.com.chiken_pix_back.chikenpix.repository.ContaBancariaRepository;
import br.com.chiken_pix_back.chikenpix.repository.TransacaoRepository;
import br.com.chiken_pix_back.chikenpix.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PixServiceTest {

    private Banco banco;
    private PixService pixService;
    private ChavePixRepository chaves;

    private final Map<String, Usuario> usuariosDb = new HashMap<>();

    @BeforeEach
    void setUp() {
        usuariosDb.clear();

        UserRepository usuarios = mock(UserRepository.class);
        ContaBancariaRepository contas = mock(ContaBancariaRepository.class);
        chaves = mock(ChavePixRepository.class);
        TransacaoRepository transacoes = mock(TransacaoRepository.class);

        when(usuarios.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario u = invocation.getArgument(0);
            usuariosDb.put(u.getId(), u);
            return u;
        });
        when(usuarios.findById(anyString())).thenAnswer(invocation ->
                Optional.ofNullable(usuariosDb.get((String) invocation.getArgument(0))));

        banco = new Banco(usuarios, contas, chaves, transacoes);
        pixService = new PixService(banco);
    }

    // Vincula a chave EMAIL da conta destino ao mock do repositório de chaves,
    // simulando o que o ChavePixRepository faria de verdade num banco real.
    private void vincularChaveEmail(Usuario destino, String email) {
        destino.getConta().addChavePix(TipoChavePix.EMAIL, email);
        ChavePix chave = destino.getConta().buscarChavePix(TipoChavePix.EMAIL);
        when(chaves.findByChave(email)).thenReturn(Optional.of(chave));
    }

    @Test
    @DisplayName("Deve realizar um Pix para outra conta com sucesso")
    void deveRealizarPixComSucesso(){
        Usuario usuarioAutenticadoOrigem = new Usuario(
                "Regis", "regis@email.com", "894.321.242-06",
                "bancodedados", null, "82976099776"
        );
        usuarioAutenticadoOrigem.getConta().creditar(BigDecimal.valueOf(100));

        Usuario usuarioAutenticadoDestino = new Usuario(
                "Camila", "camila@email.com", "124.324.244-07",
                "minecraft", null, "82976089345"
        );

        vincularChaveEmail(usuarioAutenticadoDestino, "camila@email.com");
        banco.addUsuario(usuarioAutenticadoDestino);

        pixService.realizarPix(
                usuarioAutenticadoOrigem.getConta(),
                "camila@email.com",
                BigDecimal.valueOf(40)
        );

        assertThat(usuarioAutenticadoDestino.getConta().getSaldo())
                .isEqualByComparingTo(BigDecimal.valueOf(40));
        assertThat(usuarioAutenticadoOrigem.getConta().getSaldo())
                .isEqualByComparingTo(BigDecimal.valueOf(60));
    }

    @Test
    @DisplayName("Não deve realiar pix com valor inválido")
    void naoDeveRealizarPixInvalido(){
        Usuario usuarioAutenticadoOrigem = new Usuario(
                "Regis", "regis@email.com", "894.321.242-06",
                "bancodedados", null, "82976099776"
        );
        usuarioAutenticadoOrigem.getConta().creditar(BigDecimal.valueOf(100));

        Usuario usuarioAutenticadoDestino = new Usuario(
                "Camila", "camila@email.com", "124.324.244-07",
                "minecraft", null, "82976089345"
        );

        vincularChaveEmail(usuarioAutenticadoDestino, "camila@email.com");
        banco.addUsuario(usuarioAutenticadoDestino);

        assertThatThrownBy(
                () -> pixService.realizarPix(
                        usuarioAutenticadoOrigem.getConta(),
                        "camila@email.com",
                        BigDecimal.valueOf(-40)
                )
        )
                .isInstanceOf(ValorPixInvalidoException.class)
                .hasMessage("Error: Valor inválido para realizar Pix.");
    }

    @Test
    @DisplayName("Não deve realiar pix, se a chave pix não for encontrada")
    void naoDeveRealizarPixComChaveNaoEcontrada(){
        Usuario usuarioAutenticadoOrigem = new Usuario(
                "Regis", "regis@email.com", "894.321.242-06",
                "bancodedados", null, "82976099776"
        );
        usuarioAutenticadoOrigem.getConta().creditar(BigDecimal.valueOf(100));

        Usuario usuarioAutenticadoDestino = new Usuario(
                "Camila", "camila@email.com", "124.324.244-07",
                "minecraft", null, "82976089345"
        );

        banco.addUsuario(usuarioAutenticadoDestino);
        // Sem vincular chave -> mock retorna Optional.empty() por padrão

        assertThatThrownBy(
                () -> pixService.realizarPix(
                        usuarioAutenticadoOrigem.getConta(),
                        "camila@email.com",
                        BigDecimal.valueOf(40)
                )
        )
                .isInstanceOf(ChaveNaoEncontradaException.class)
                .hasMessage("Error: Chave Pix não encontrada.");
    }

    @Test
    @DisplayName("Não deve realizar pix, se o saldo não for suficiente")
    void naoDeveRealizarPixComSaldoInsuficiente(){
        Usuario usuarioAutenticadoOrigem = new Usuario(
                "Regis", "regis@email.com", "894.321.242-06",
                "bancodedados", null, "82976099776"
        );
        usuarioAutenticadoOrigem.getConta().creditar(BigDecimal.valueOf(50));

        Usuario usuarioAutenticadoDestino = new Usuario(
                "Camila", "camila@email.com", "124.324.244-07",
                "minecraft", null, "82976089345"
        );

        vincularChaveEmail(usuarioAutenticadoDestino, "camila@email.com");
        banco.addUsuario(usuarioAutenticadoDestino);

        assertThatThrownBy(
                () -> pixService.realizarPix(
                        usuarioAutenticadoOrigem.getConta(),
                        "camila@email.com",
                        BigDecimal.valueOf(70)
                )
        )
                .isInstanceOf(SaldoInsuficienteException.class)
                .hasMessage("Error: Saldo insuficiente para realizar Pix.");
    }

    @Test
    @DisplayName("Não deve alterar o saldo da conta origem, se a chave pix não for encontrada")
    void naoDeveAlterarSaldoComChaveNaoEcontrada(){
        Usuario usuarioAutenticadoOrigem = new Usuario(
                "Regis", "regis@email.com", "894.321.242-06",
                "bancodedados", null, "82976099776"
        );
        usuarioAutenticadoOrigem.getConta().creditar(BigDecimal.valueOf(100));

        Usuario usuarioAutenticadoDestino = new Usuario(
                "Camila", "camila@email.com", "124.324.244-07",
                "minecraft", null, "82976089345"
        );

        banco.addUsuario(usuarioAutenticadoDestino);

        assertThatThrownBy(
                () -> pixService.realizarPix(
                        usuarioAutenticadoOrigem.getConta(),
                        "camila@email.com",
                        BigDecimal.valueOf(40)
                )
        ).isInstanceOf(ChaveNaoEncontradaException.class);

        assertThat(usuarioAutenticadoOrigem.getConta().getSaldo())
                .isEqualByComparingTo(BigDecimal.valueOf(100));
    }

    @Test
    @DisplayName("Não deve alterar o saldo do destino e origem, se o saldo não for suficiente")
    void naoDeveAlterarSaldoComSaldoInsuficiente(){
        Usuario usuarioAutenticadoOrigem = new Usuario(
                "Regis", "regis@email.com", "894.321.242-06",
                "bancodedados", null, "82976099776"
        );

        Usuario usuarioAutenticadoDestino = new Usuario(
                "Camila", "camila@email.com", "124.324.244-07",
                "minecraft", null, "82976089345"
        );

        vincularChaveEmail(usuarioAutenticadoDestino, "camila@email.com");
        banco.addUsuario(usuarioAutenticadoDestino);

        assertThatThrownBy(
                () -> pixService.realizarPix(
                        usuarioAutenticadoOrigem.getConta(),
                        "camila@email.com",
                        BigDecimal.valueOf(70)
                )
        ).isInstanceOf(SaldoInsuficienteException.class);

        assertThat(usuarioAutenticadoDestino.getConta().getSaldo())
                .isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(usuarioAutenticadoOrigem.getConta().getSaldo())
                .isEqualByComparingTo(BigDecimal.ZERO);
    }
}