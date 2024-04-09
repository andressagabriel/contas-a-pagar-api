package br.uece.spring.api.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.*;

@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor; 

    @Column(name = "vencimento", nullable = false)
    private LocalDate vencimento;

    @Column(name = "taxa_juros", nullable = false)
    private double taxaJuros;

    @Column(name = "paga", nullable = false)
    private boolean paga;

    @Column(name = "dias_atraso", nullable = false)
    private int diasAtraso;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal; 

    @Column(name = "juros", nullable = false)
    private BigDecimal juros;

    private boolean vencida(LocalDate dataHoje) {
        return this.getVencimento().isBefore(dataHoje) && !this.isPaga();
    }

    public int getDiasAtraso(LocalDate dataHoje) {
        if (this.vencida(dataHoje)) {
            this.setDiasAtraso((int)ChronoUnit.DAYS.between(this.getVencimento(), dataHoje));
        } else {
            this.setDiasAtraso(0);
        }

        return this.getDiasAtraso();
    }

    public BigDecimal getValorAtualizado(LocalDate dataHoje) {
        var valor = this.getValor();
        var diasAtraso = this.getDiasAtraso(dataHoje);

        while (diasAtraso > 0) {
            var fator = BigDecimal.valueOf((this.getTaxaJuros() / 100) + 1);
            valor = valor.multiply(fator);
            diasAtraso--;
        }

        valor = valor.setScale(2, RoundingMode.UP);
        this.setJuros(valor.subtract(this.getValor()));
        return valor;
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