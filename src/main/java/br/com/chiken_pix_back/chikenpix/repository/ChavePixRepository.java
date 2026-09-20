package br.com.chiken_pix_back.chikenpix.repository;

import br.com.chiken_pix_back.chikenpix.model.ChavePix;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChavePixRepository extends JpaRepository<ChavePix, Long> {
    Optional<ChavePix> findByChave(String chave);
}