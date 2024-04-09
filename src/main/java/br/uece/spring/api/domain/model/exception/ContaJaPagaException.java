package br.uece.spring.api.domain.model.exception;

public class ContaJaPagaException extends RuntimeException {
    public ContaJaPagaException(String nome) {
        super("Conta "+ nome + " já paga.");
    }
}
