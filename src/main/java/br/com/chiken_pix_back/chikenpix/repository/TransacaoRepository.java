package br.com.chiken_pix_back.chikenpix.repository;

import br.com.chiken_pix_back.chikenpix.model.ContaBancaria;
import br.com.chiken_pix_back.chikenpix.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, String>{

    List<Transacao> findByOrigemOrDestinoAndDataHoraBetween(
            ContaBancaria origem, ContaBancaria destino, LocalDateTime inicio, LocalDateTime fim
    );
}
