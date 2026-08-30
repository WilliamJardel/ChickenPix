package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.exception.EmailInvalidoException;

import br.com.chiken_pix_back.chikenpix.exception.TelefoneInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChaveTelefoneTest {

    @Test
    void deveCriarChaveTelefoneValida() {

        ChaveTelefone chave = new ChaveTelefone("+5562985977704");

        assertEquals("+5562985977704", chave.getChave());
        assertEquals(TipoChavePix.TELEFONE, chave.getTipoChave());
        assertTrue(chave.validar());
    }

    @Test
    void deveLancarExcecaoParaTelefoneInvalido() {

        ChaveTelefone chave = new ChaveTelefone("+55129111");

        assertThrows(
                TelefoneInvalidoException.class,
                chave::validar
        );
    }

    @Test
    void deveLancarExcecaoParaTelefoneVazio() {

        ChaveTelefone chave = new ChaveTelefone("");

        assertThrows(
                TelefoneInvalidoException.class,
                chave::validar
        );
    }
}
