package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.exception.CPFInvalidoException;
import br.com.chiken_pix_back.chikenpix.exception.IdNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BancoTest {
    private Banco banco;

    @BeforeEach
    void setUp(){
        banco = new Banco();
    }

    @Test
    @DisplayName("Deve cadastrar um usuário com CPF com sucesso")
    void deveCadastrarUsuarioComCpf() {
        Usuario usuario = banco.cadastrarUsuario(
                "Keyt",
                "silva@email.com",
                "047.020.860-80",
                "Senha@123",
                null,
                "88999998888"
        );

        assertThat(usuario).isNotNull();
        assertThat(banco.getUsuario(usuario.getId())).isEqualTo(usuario);
    }

    @Test
    @DisplayName("Deve lançar exceção se preencher CPF e CNPJ juntos")
    void deveLancarExcecaoAoPreencherCpfECnpjJuntos() {
        assertThatThrownBy(
                () -> banco.cadastrarUsuario(
                    "Empresa",
                    "emp@email.com",
                    "52998224725",
                    "Senha@123",
                    "12345678000199",
                    "88999998888"
                )
        )
                .isInstanceOf(CPFInvalidoException.class)
                .hasMessageContaining(
                        "Erro: Você não pode preencher CPF e CNPJ ao mesmo tempo."
                );
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar remover usuário com ID inexistente")
    void deveLancarExcecaoAoRemoverUsuarioInexistente() {
        assertThatThrownBy(
                () -> banco.removerUsuario(
                        "id-inexistente-123"
                )
        )
                .isInstanceOf(IdNaoEncontradoException.class)
                .hasMessageContaining(
                        "Error: Usuario não encontrado"
                );
    }

    @Test
    @DisplayName("Deve remover um usuário existente com sucesso")
    void deveRemoverUsuarioComSucesso() {
        Usuario usuario = banco.cadastrarUsuario(
                "Carlos",
                "carlos@email.com",
                "946.950.340-30",
                "Senha@123",
                null,
                "88999998888"
        );

        banco.removerUsuario(usuario.getId());

        assertThat(banco.getUsuario(usuario.getId())).isNull();
    }

    @Test
    @DisplayName("Deve encontrar uma conta pela chave PIX CPF")
    void deveEncontrarContaPelaChaveCpf() {
        Usuario usuario = banco.cadastrarUsuario(
                "Carlos",
                "carlos@email.com",
                "946.950.340-30",
                "Senha@123",
                null,
                "88999998888"
        );

        ContaBancaria conta = usuario.getConta();

        ChavePix chave = new ChaveCpf("946.950.340-30");

        conta.addChavePix(TipoChavePix.CPF, chave);

        ContaBancaria encontrada = banco.buscarConta("946.950.340-30");

        assertThat(encontrada).isEqualTo(conta);

    }

    @Test
    @DisplayName("Deve encontrar uma conta pela chave PIX email")
    void deveEncontrarContaPelaChaveEmail() {
        Usuario usuario = banco.cadastrarUsuario(
                "Carlos",
                "carlos@email.com",
                "946.950.340-30",
                "Senha@123",
                null,
                "88999998888"
        );

        ContaBancaria conta = usuario.getConta();

        conta.addChavePix(TipoChavePix.EMAIL, new ChaveEmail("carlos@email.com"));

        ContaBancaria encontrada = banco.buscarConta("carlos@email.com");

        assertThat(encontrada).isEqualTo(conta);

    }

    //refatorar com o metodo de buscar conta no Banco
    @Test
    @DisplayName("Deve retornar null quando a chave PIX nao existir")
    void deveRetornarNullQuandoChaveNaoExistir() {
        Usuario usuario = banco.cadastrarUsuario(
                "Carlos",
                "carlos@email.com",
                "946.950.340-30",
                "Senha@123",
                null,
                "88999998888"
        );

        usuario.getConta().addChavePix(TipoChavePix.CPF, new ChaveCpf("946.950.340-30"));

        ContaBancaria encontrada = banco.buscarConta("111.222.333-44");

        assertThat(encontrada).isNull();


    }
}