package br.com.chiken_pix_back.chikenpix.repository;

import br.com.chiken_pix_back.chikenpix.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, String>{

}
