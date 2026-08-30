package br.com.chiken_pix_back.chikenpix.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChaveAleatoriaTest {

    @Test
    void deveGerarChaveAleatoriaValida() {

        ChaveAleatoria chave = new ChaveAleatoria();

        assertNotNull(chave.getChave());
        assertEquals(TipoChavePix.ALEATORIA, chave.getTipoChave());
        assertTrue(chave.validar());
    }
}
