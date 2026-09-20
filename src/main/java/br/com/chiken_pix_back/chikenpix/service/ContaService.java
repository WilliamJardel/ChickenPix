package br.com.chiken_pix_back.chikenpix.service;

import br.com.chiken_pix_back.chikenpix.model.Banco;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
    private Banco banco;

    public ContaService(Banco banco){
        this.banco = banco;
    }


}
