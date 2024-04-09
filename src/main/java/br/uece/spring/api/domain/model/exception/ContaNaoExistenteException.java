package br.uece.spring.api.domain.model.exception;

public class ContaNaoExistenteException extends RuntimeException {
    public ContaNaoExistenteException() {
        super("Conta não existente.");
    }
}
