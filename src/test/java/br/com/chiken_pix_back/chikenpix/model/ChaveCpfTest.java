package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.exception.CPFInvalidoException;
import br.com.chiken_pix_back.chikenpix.exception.EmailInvalidoException;

import br.com.chiken_pix_back.chikenpix.exception.TelefoneInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChaveCpfTest {

    @Test
    void deveCriarChaveCpfValida() {

        ChaveCpf chave = new ChaveCpf("079.891.052-09");

        assertEquals("079.891.052-09", chave.getChave());
        assertEquals(TipoChavePix.CPF, chave.getTipoChave());
        assertTrue(chave.validar());
    }

    @Test
    void deveLancarExcecaoParaCPFInvalido() {

        ChaveCpf chave = new ChaveCpf("079891052091");

        assertThrows(
                CPFInvalidoException.class,
                chave::validar
        );
    }

    @Test
    void deveLancarExcecaoParaCPFVazio() {

        ChaveCpf chave = new ChaveCpf("");

        assertThrows(
                CPFInvalidoException.class,
                chave::validar
        );
    }
}