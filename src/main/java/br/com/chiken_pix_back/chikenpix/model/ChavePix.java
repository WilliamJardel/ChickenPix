package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.enumerations.TipoChavePix;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "chaves_pix")
public class ChavePix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    @Getter
    private TipoChavePix tipoChave;

    @Column(name = "chave")
    @Getter
    private String chave;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private ContaBancaria contaBancaria;

    protected ChavePix() {}

    public ChavePix(TipoChavePix tipoChave, String chave, ContaBancaria contaBancaria) {
        this.tipoChave = tipoChave;
        this.chave = chave;
        this.contaBancaria = contaBancaria;
        // sem gerar id manualmente — o banco gera sozinho (BIGSERIAL)
    }
}