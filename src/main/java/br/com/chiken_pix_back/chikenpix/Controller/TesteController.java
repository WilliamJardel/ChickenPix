package br.com.chiken_pix_back.chikenpix.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TesteController {

    @GetMapping("/teste")
    public String teste() {
        return "API do ChikenPIX está no ar!";
    }
}