package br.com.chiken_pix_back.chikenpix.controller;

import br.com.chiken_pix_back.chikenpix.model.Banco;
import br.com.chiken_pix_back.chikenpix.model.Usuario;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banco")
public class BancoController {

    private final Banco banco;

    public BancoController(Banco banco) {
        this.banco = banco;
    }

    @PostMapping("/usuarios")
    public String adicionarUsuario(@RequestBody Usuario usuario) {

        banco.addUsuario(usuario);

        return "Usuário adicionado com sucesso!";
    }
}
