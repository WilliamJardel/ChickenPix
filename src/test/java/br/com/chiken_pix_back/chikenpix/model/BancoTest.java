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
    @DisplayName("Deve cadastrar um usuário com CNPJ com sucesso quando CPF for nulo")
    void deveCadastrarUsuarioComCnpjComSucesso() {
        Usuario usuario = banco.cadastrarUsuario(
                "Empresa Chiken Pix Ltda",
                "contato@chikenpix.com",
                null,                   // CPF é nulo
                "Senha@123",
                "11.222.333/0001-81",   // CNPJ informado
                "88999998888"
        );


        assertThat(usuario).isNotNull();
        assertThat(usuario.getCnpj()).isEqualTo("11.222.333/0001-81");
        assertThat(usuario.getCpf()).isNull();
        assertThat(banco.getUsuario(usuario.getId())).isEqualTo(usuario);
    }

    @Test
    @DisplayName("Deve lançar exceção se tentar cadastrar sem CPF e sem CNPJ ao mesmo tempo")
    void deveLancarExcecaoAoCadastrarSemCpfESemCnpj() {
        assertThatThrownBy(
                () -> banco.cadastrarUsuario(
                        "Usuário Sem Documento",
                        "semdoc@email.com",
                        null, // CPF nulo
                        "Senha@123",
                        null, // CNPJ nulo
                        "88999998888"
                )
        )
                .isInstanceOf(CPFInvalidoException.class);

    }
}