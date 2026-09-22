package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.enumerations.TipoChavePix;
import br.com.chiken_pix_back.chikenpix.enumerations.TipoTransacao;
import br.com.chiken_pix_back.chikenpix.exception.CPFInvalidoException;
import br.com.chiken_pix_back.chikenpix.exception.IdNaoEncontradoException;
import br.com.chiken_pix_back.chikenpix.repository.ChavePixRepository;
import br.com.chiken_pix_back.chikenpix.repository.ContaBancariaRepository;
import br.com.chiken_pix_back.chikenpix.repository.TransacaoRepository;
import br.com.chiken_pix_back.chikenpix.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BancoTest {
    private Banco banco;
    private UserRepository usuarios;
    private ContaBancariaRepository contas;
    private ChavePixRepository chaves;
    private TransacaoRepository transacoes;

    private final Map<String, Usuario> usuariosDb = new HashMap<>();

    @BeforeEach
    void setUp() {
        usuariosDb.clear();

        usuarios = mock(UserRepository.class);
        contas = mock(ContaBancariaRepository.class);
        chaves = mock(ChavePixRepository.class);
        transacoes = mock(TransacaoRepository.class);

        // Simula um "banco de dados" de usuários em memória
        when(usuarios.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario u = invocation.getArgument(0);
            usuariosDb.put(u.getId(), u);
            return u;
        });
        when(usuarios.findById(anyString())).thenAnswer(invocation ->
                Optional.ofNullable(usuariosDb.get((String) invocation.getArgument(0))));
        when(usuarios.existsById(anyString())).thenAnswer(invocation ->
                usuariosDb.containsKey((String) invocation.getArgument(0)));
        doAnswer(invocation -> {
            usuariosDb.remove((String) invocation.getArgument(0));
            return null;
        }).when(usuarios).deleteById(anyString());

        banco = new Banco(usuarios, contas, chaves, transacoes);
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

        assertThatThrownBy(() -> banco.getUsuario(usuario.getId()))
                .isInstanceOf(IdNaoEncontradoException.class);
    }

    @Test
    @DisplayName("Deve cadastrar um usuário com CNPJ com sucesso quando CPF for nulo")
    void deveCadastrarUsuarioComCnpjComSucesso() {
        Usuario usuario = banco.cadastrarUsuario(
                "Empresa Chiken Pix Ltda",
                "contato@chikenpix.com",
                null,
                "Senha@123",
                "11.222.333/0001-81",
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
                        null,
                        "Senha@123",
                        null,
                        "88999998888"
                )
        )
                .isInstanceOf(CPFInvalidoException.class);
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
        conta.addChavePix(TipoChavePix.CPF, "946.950.340-30");
        ChavePix chave = conta.buscarChavePix(TipoChavePix.CPF);

        when(chaves.findByChave("946.950.340-30")).thenReturn(Optional.of(chave));

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
        conta.addChavePix(TipoChavePix.EMAIL, "carlos@email.com");
        ChavePix chave = conta.buscarChavePix(TipoChavePix.EMAIL);

        when(chaves.findByChave("carlos@email.com")).thenReturn(Optional.of(chave));

        ContaBancaria encontrada = banco.buscarConta("carlos@email.com");

        assertThat(encontrada).isEqualTo(conta);
    }

    @Test
    @DisplayName("Deve retornar null quando a chave PIX nao existir")
    void deveRetornarNullQuandoChaveNaoExistir() {
        banco.cadastrarUsuario(
                "Carlos",
                "carlos@email.com",
                "946.950.340-30",
                "Senha@123",
                null,
                "88999998888"
        );

        // Nenhum stub configurado para "111.222.333-44" -> mock retorna Optional.empty() por padrão
        ContaBancaria encontrada = banco.buscarConta("111.222.333-44");

        assertThat(encontrada).isNull();
    }

    @Test
    @DisplayName("Deve encontrar a conta correta entre varios usuarios")
    void deveEncontrarContaCorretaEntreUsuarios() {
        Usuario usuario1 = banco.cadastrarUsuario(
                "Carlos",
                "carlos@email.com",
                "946.950.340-30",
                "Senha@123",
                null,
                "88999998888"
        );

        Usuario usuario2 = banco.cadastrarUsuario(
                "Pedro",
                "pedro@email.com",
                "945.950.340-30",
                "Senha@375",
                null,
                "88999997888"
        );

        ContaBancaria conta1 = usuario1.getConta();
        ContaBancaria conta2 = usuario2.getConta();

        conta1.addChavePix(TipoChavePix.CPF, "946.950.340-30");
        conta2.addChavePix(TipoChavePix.CPF, "945.950.340-30");

        when(chaves.findByChave("945.950.340-30"))
                .thenReturn(Optional.of(conta2.buscarChavePix(TipoChavePix.CPF)));

        ContaBancaria encontrada = banco.buscarConta("945.950.340-30");

        assertThat(encontrada).isEqualTo(conta2);
    }

    @Test
    @DisplayName("Deve encontrar uma conta pela chave PIX Telefone")
    void deveEncontrarContaPelaChaveTelefone() {
        Usuario usuario = banco.cadastrarUsuario(
                "Carlos",
                "carlos@email.com",
                "946.950.340-30",
                "Senha@123",
                null,
                "88999998888"
        );

        ContaBancaria conta = usuario.getConta();
        conta.addChavePix(TipoChavePix.TELEFONE, "+5588999998888");
        ChavePix chave = conta.buscarChavePix(TipoChavePix.TELEFONE);

        when(chaves.findByChave("+5588999998888")).thenReturn(Optional.of(chave));

        ContaBancaria encontrada = banco.buscarConta("+5588999998888");

        assertThat(encontrada).isEqualTo(conta);
    }

    @Test
    @DisplayName("Deve encontrar uma conta pela chave PIX CNPJ")
    void deveEncontrarContaPelaChaveCnpj() {
        Usuario usuario = banco.cadastrarUsuario(
                "Carlos",
                "carlos@email.com",
                null,
                "Senha@123",
                "11222333000181",
                "88999998888"
        );

        ContaBancaria conta = usuario.getConta();
        conta.addChavePix(TipoChavePix.CNPJ, "11222333000181");
        ChavePix chave = conta.buscarChavePix(TipoChavePix.CNPJ);

        when(chaves.findByChave("11222333000181")).thenReturn(Optional.of(chave));

        ContaBancaria encontrada = banco.buscarConta("11222333000181");

        assertThat(encontrada).isEqualTo(conta);
    }

    @Test
    @DisplayName("Deve encontrar uma conta pela chave PIX Aleatoria")
    void deveEncontrarContaPelaChaveAleatoria() {
        Usuario usuario = banco.cadastrarUsuario(
                "Carlos",
                "carlos@email.com",
                "946.950.340-30",
                "Senha@123",
                null,
                "88999998888"
        );

        ContaBancaria conta = usuario.getConta();
        ChavePix chave = conta.gerarChaveAleatoria();

        when(chaves.findByChave(chave.getChave())).thenReturn(Optional.of(chave));

        ContaBancaria encontrada = banco.buscarConta(chave.getChave());

        assertThat(encontrada).isEqualTo(conta);
    }

    @Test
    @DisplayName("Deve listar notificacoes (transacoes) de uma conta, mais recente primeiro")
    void deveListarNotificacoesDaConta() {
        Usuario usuario = banco.cadastrarUsuario(
                "Carlos", "carlos@email.com", "946.950.340-30",
                "Senha@123", null, "88999998888"
        );

        ContaBancaria conta = usuario.getConta();

        when(contas.findById(conta.getNumeroConta())).thenReturn(Optional.of(conta));

        Transacao t1 = new Transacao(conta, conta, new BigDecimal("10.00"), TipoTransacao.PIX_ENVIADO);
        Transacao t2 = new Transacao(conta, conta, new BigDecimal("20.00"), TipoTransacao.PIX_ENVIADO);

        when(transacoes.findByOrigem_NumeroContaOrDestino_NumeroContaOrderByDataHoraDesc(
                conta.getNumeroConta(), conta.getNumeroConta()))
                .thenReturn(List.of(t2, t1));

        List<Transacao> resultado = banco.consultarNotificacoes(conta.getNumeroConta());

        assertThat(resultado).containsExactly(t2, t1);
    }
}