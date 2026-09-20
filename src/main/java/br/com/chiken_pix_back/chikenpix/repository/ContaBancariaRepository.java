package br.com.chiken_pix_back.chikenpix.repository;

import br.com.chiken_pix_back.chikenpix.model.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, String> {
}
