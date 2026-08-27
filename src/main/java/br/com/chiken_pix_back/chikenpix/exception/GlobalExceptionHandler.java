package br.com.chiken_pix_back.chikenpix.exception;

import br.com.caelum.stella.tinytype.CPF;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ChaveNaoEncontradaException.class)
    public ResponseEntity<String> tratarChaveNaoEncontrada(ChaveNaoEncontradaException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ValorPixInvalidoException.class)
    public ResponseEntity<String> tratarValorPixInvalido(ValorPixInvalidoException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ChavePixJaCadastradaException.class)
    public ResponseEntity<String> tratarChavePixJaCadastrada(ChavePixJaCadastradaException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ChavePixJaCadastradaException.class)
    public ResponseEntity<String> tratarSenhaInvalida(SenhaInvalidaException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ChavePixJaCadastradaException.class)
    public ResponseEntity<String> tratarEmailInvalido(EmailInvalidoException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ChavePixJaCadastradaException.class)
    public ResponseEntity<String> tratarNomeInvalido(NomeInvalidoException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ChavePixJaCadastradaException.class)
    public ResponseEntity<String> tratarCPFInvalido(CPFInvalidoException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }
}
