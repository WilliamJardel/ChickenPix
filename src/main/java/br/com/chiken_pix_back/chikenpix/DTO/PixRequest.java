package br.com.chiken_pix_back.chikenpix.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PixRequest {

    private String origem;
    private String chaveDestino;
    private BigDecimal valor;
}
