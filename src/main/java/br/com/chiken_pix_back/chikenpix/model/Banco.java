package br.com.chiken_pix_back.chikenpix.model;

import java.util.HashMap;
import br.com.chiken_pix_back.chikenpix.model.ContaBancaria;

public class Banco {
    private HashMap<Integer, Usuarios> usuarios;

    public Banco() {
        this.usuarios = new HashMap<>(Integer, Usuario);
    }

    public Usuario getUsuario(int id) {
    }

    public void addUsuario(Usuario usuario) {
    }

    public Usuario rmUsuario(int id) {
    }

    public ContaBancaria buscarConta(String chave) {
    }


}
