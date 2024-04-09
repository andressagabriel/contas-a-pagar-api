package br.uece.spring.api.domain.model.exception;

public class ContaJaExistenteException extends RuntimeException {
    public ContaJaExistenteException(String nome) {
        super("Conta "+ nome +"  já existente.");
    }
}
