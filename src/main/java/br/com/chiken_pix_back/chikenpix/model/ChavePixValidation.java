package br.com.chiken_pix_back.chikenpix.model;

import br.com.chiken_pix_back.chikenpix.enumerations.TipoChavePix;
import br.com.chiken_pix_back.chikenpix.exception.CNPJInvalidoException;
import br.com.chiken_pix_back.chikenpix.exception.CPFInvalidoException;
import br.com.chiken_pix_back.chikenpix.exception.EmailInvalidoException;
import br.com.chiken_pix_back.chikenpix.exception.TelefoneInvalidoException;
import org.jspecify.annotations.NonNull;

public class ChavePixValidation{

    private ChavePixValidation() {} // classe utilitária, não deve ser instanciada

    public static void validar(TipoChavePix tipo, String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("Valor da chave Pix não pode ser vazio.");
        }

        switch (tipo) {
            case CPF -> validarCpf(valor);
            case CNPJ -> validarCnpj(valor);
            case EMAIL -> validarEmail(valor);
            case TELEFONE -> validarTelefone(valor);
            case ALEATORIA -> throw new IllegalStateException(
                    "Chave aleatória não deve passar por validação manual — use gerarChaveAleatoria().");
        }
    }

    private static void validarCpf(@NonNull String valor) {
        if (!valor.trim().matches("^(\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$")) {
            throw new CPFInvalidoException("CPF inválido para chave Pix.");
        }
    }

    private static void validarCnpj(@NonNull String valor) {
        if (!valor.trim().matches("^(\\d{14}|\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2})$")) {
            throw new CNPJInvalidoException("CNPJ inválido para chave Pix.");
        }
    }

    private static void validarEmail(@NonNull String valor) {
        if (!valor.trim().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new EmailInvalidoException("E-mail inválido para chave Pix.");
        }
    }

    private static void validarTelefone(@NonNull String valor) {
        if (!valor.trim().matches("^\\+?\\d{10,13}$")) {
            throw new TelefoneInvalidoException("Telefone inválido para chave Pix.");
        }
    }
}