package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.exception.EmailInvalidoException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChaveEmailTest {

    @Test
    void deveCriarChaveEmailValida() {

        ChaveEmail chave = new ChaveEmail("kimila-zuiliam-naira-pedrokam-teste@gmail.com");

        assertEquals("kimila-zuiliam-naira-pedrokam-teste@gmail.com", chave.getChave());
        assertEquals(TipoChavePix.EMAIL, chave.getTipoChave());
        assertTrue(chave.validar());
    }

    @Test
    void deveLancarExcecaoParaEmailInvalido() {

        ChaveEmail chave = new ChaveEmail("email-invalido");

        assertThrows(
                EmailInvalidoException.class,
                chave::validar
        );
    }

    @Test
    void deveLancarExcecaoParaEmailVazio() {

        ChaveEmail chave = new ChaveEmail("");

        assertThrows(
                EmailInvalidoException.class,
                chave::validar
        );
    }
}