package br.com.chiken_pix_back.chikenpix.controller;

import br.com.chiken_pix_back.chikenpix.model.Banco;
import br.com.chiken_pix_back.chikenpix.model.Transacao;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final Banco banco;

    public TransacaoController(Banco banco) {
        this.banco = banco;
    }

    @GetMapping
    public List<Transacao> listarTodas() {
        return banco.listarTransacoes();
    }

    @GetMapping("/filtro")
    public List<Transacao> filtrarPorData(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return banco.filtrarTransacoesPorData(inicio, fim);
    }

    @PatchMapping("/{id}/cancelar")
    public void cancelarTransacao(@PathVariable String id) {
        banco.cancelarTransacao(id);
    }

    @PatchMapping("/{id}/concluir")
    public void concluirTransacao(@PathVariable String id) {
        banco.concluirTransacao(id);
    }
}