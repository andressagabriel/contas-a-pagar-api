package br.uece.spring.api.application.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import br.uece.spring.api.domain.model.Conta;

public class NovaContaDto {
    
    private String nome;
    private BigDecimal valor;
    private double taxaJuros;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate vencimento;
    private static LocalDate dataHoje = LocalDate.now();

    public Conta toEntity() {
        Conta entity = new Conta();
        entity.setNome(nome);
        entity.setValor(valor);
        entity.setTaxaJuros(taxaJuros);
        entity.setVencimento(vencimento);
        entity.setValorTotal(entity.getValorAtualizado(dataHoje));
        return entity;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }
}
