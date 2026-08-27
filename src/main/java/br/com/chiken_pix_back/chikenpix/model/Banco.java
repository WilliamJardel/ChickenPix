package br.com.chiken_pix_back.chikenpix.model;

import java.util.HashMap;

public class Banco {
    private HashMap<Integer, Usuario> usuarios;

    public Banco() {
        this.usuarios = new HashMap<Integer, Usuario>();
    }

    public Usuario getUsuario(int id) {
        return usuarios.get(id);
    }

    public void addUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

    public Usuario rmUsuario(int id) {
        return usuarios.remove(id);
    }

    public ContaBancaria buscarConta(String chave) {
        for (Usuario usuario: usuarios.values()) {
            ContaBancaria conta = usuario.getConta();

            for (TipoChavePix tipoChavePix : TipoChavePix.values()) {
                ChavePix chavePix = conta.buscarChavePix(tipoChavePix);

                if (chavePix != null && chavePix.getChave().equals(chave)) {
                    return conta;
                }
            }

        }
        return null;
    }

}
