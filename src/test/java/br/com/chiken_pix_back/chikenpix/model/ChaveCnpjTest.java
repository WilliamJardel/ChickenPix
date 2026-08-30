package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.exception.CNPJInvalidoException;
import br.com.chiken_pix_back.chikenpix.exception.CPFInvalidoException;
import br.com.chiken_pix_back.chikenpix.exception.EmailInvalidoException;

import br.com.chiken_pix_back.chikenpix.exception.TelefoneInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChaveCnpjTest {

    @Test
    void deveCriarChaveCNPJValida() {

        ChaveCnpj chave = new ChaveCnpj("11222333000181");

        assertEquals("11222333000181", chave.getChave());
        assertEquals(TipoChavePix.CNPJ, chave.getTipoChave());
        assertTrue(chave.validar());
    }

    @Test
    void deveLancarExcecaoParaCNPJInvalido() {

        ChaveCnpj chave = new ChaveCnpj("0000000000191");

        assertThrows(
                CNPJInvalidoException.class,
                chave::validar
        );
    }

    @Test
    void deveLancarExcecaoParaCNPJVazio() {

        ChaveCnpj chave = new ChaveCnpj("");

        assertThrows(
                CNPJInvalidoException.class,
                chave::validar
        );
    }
}