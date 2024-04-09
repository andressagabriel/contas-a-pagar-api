package br.uece.spring.api.application.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import br.uece.spring.api.domain.model.Conta;

public class RetornoContaDto {
    private int codigo;
    private String nome;
    private double taxaJuros;
    private int diasAtraso;
    private boolean paga;
    private BigDecimal valor;
    private BigDecimal juros;
    private BigDecimal valorTotal;
    

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate vencimento;
    private static LocalDate dataHoje = LocalDate.now();

    public static RetornoContaDto from(Conta entity, boolean updated) {
        RetornoContaDto dto = new RetornoContaDto();
        dto.setCodigo(entity.getCodigo());
        dto.setPaga(entity.isPaga());
        dto.setNome(entity.getNome());
        dto.setValor(entity.getValor());
        dto.setTaxaJuros(entity.getTaxaJuros());
        dto.setVencimento(entity.getVencimento());
        if (entity.isPaga() || updated) {
            dto.setValorTotal(entity.getValorTotal());
        } else {
            dto.setValorTotal(entity.getValorAtualizado(dataHoje));
        }
        dto.setDiasAtraso(entity.getDiasAtraso());
        dto.setJuros(entity.getJuros());
        return dto;
    }

    public static List<RetornoContaDto> from(List<Conta> contas) {
        return contas.stream()
            .map(conta -> RetornoContaDto.from(conta, false))
            .collect(Collectors.toList());
    }

    public Conta toEntity() {
        Conta entity = new Conta();
        entity.setCodigo(codigo);
        entity.setPaga(paga);
        entity.setNome(nome);
        entity.setValor(valor);
        entity.setTaxaJuros(taxaJuros);
        entity.setVencimento(vencimento);
        entity.setValorTotal(valorTotal);
        entity.setDiasAtraso(diasAtraso);
        entity.setJuros(juros);
        return entity;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public boolean isPaga() {
        return paga;
    }

    public void setPaga(boolean paga) {
        this.paga = paga;
    }

    public int getDiasAtraso() {
        return diasAtraso;
    }

    public void setDiasAtraso(int diasAtraso) {
        this.diasAtraso = diasAtraso;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getJuros() {
        return juros;
    }

    public void setJuros(BigDecimal juros) {
        this.juros = juros;
    }
}
