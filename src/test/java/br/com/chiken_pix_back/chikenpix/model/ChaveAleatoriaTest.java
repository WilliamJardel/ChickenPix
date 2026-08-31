package br.com.chiken_pix_back.chikenpix.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ChaveAleatoriaTest {

    @Test
    @DisplayName("Deve gerar uma chave Pix aleatória válida")
    void deveGerarChaveAleatoriaValida() {

        ChaveAleatoria chave = new ChaveAleatoria();

        assertThat(chave.getChave()).isNotNull();
        assertThat(chave.getTipoChave()).isEqualTo(TipoChavePix.ALEATORIA);
        assertThat(chave.validar()).isTrue();
    }
}
