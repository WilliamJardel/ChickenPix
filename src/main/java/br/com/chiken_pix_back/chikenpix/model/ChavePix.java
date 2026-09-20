package br.com.chiken_pix_back.chikenpix.model;

import jakarta.persistence.*;
import lombok.Getter;
import java.util.UUID;

@Entity
@Table(name = "chave_pix")
public class ChavePix {

    @Id
    @Getter
    private String id;

    @Enumerated(EnumType.STRING)
    @Getter
    private TipoChavePix tipoChave;

    @Getter
    private String chave;

    @ManyToOne
    @JoinColumn(name = "conta_bancaria_id")
    private ContaBancaria contaBancaria;

    protected ChavePix() {} // construtor vazio exigido pelo JPA

    public ChavePix(TipoChavePix tipoChave, String chave, ContaBancaria contaBancaria) {
        this.id = UUID.randomUUID().toString();
        this.tipoChave = tipoChave;
        this.chave = chave;
        this.contaBancaria = contaBancaria;
    }
}