package br.edu.iff.ProjetoImobiliaria.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Aluga extends Contrato {

    @Column(nullable = false, columnDefinition = "DATE")
    @NotNull
    private LocalDate dataInicio;
    @Column(nullable = false, columnDefinition = "DATE")
    @NotNull
    private LocalDate dataFim;
    @Column(nullable = false)
    @NotNull
    @Positive
    private float valorMensalidade;

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public float getValorMensalidade() {
        return valorMensalidade;
    }

    public void setValorMensalidade(float valorMensalidade) {
        this.valorMensalidade = valorMensalidade;
    }

}
