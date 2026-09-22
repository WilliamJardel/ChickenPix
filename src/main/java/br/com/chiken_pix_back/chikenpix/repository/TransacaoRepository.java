package br.com.chiken_pix_back.chikenpix.repository;

import br.com.chiken_pix_back.chikenpix.model.ContaBancaria;
import br.com.chiken_pix_back.chikenpix.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, String>{

    List<Transacao> findByDataHoraBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);
    //basicamente procura todas as transações entre duas datas


    //notação especifica pq se deixasse o Sprig buscar automaticamente o filtro não seria obedecido
    //
    @Query("SELECT t FROM Transacao t WHERE (t.origem = :origem OR t.destino = :destino) AND t.dataHora BETWEEN :inicio AND :fim")
    List<Transacao> findByOrigemOrDestinoAndDataHoraBetween(
            @Param("origem") ContaBancaria origem,
            @Param("destino") ContaBancaria destino,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );
}
