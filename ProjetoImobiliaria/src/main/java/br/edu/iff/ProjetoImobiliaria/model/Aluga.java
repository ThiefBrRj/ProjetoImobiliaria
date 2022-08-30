package br.edu.iff.ProjetoImobiliaria.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Aluga extends Contrato{
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    @NotNull
    private Date dataInicio;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    @NotNull
    private Date dataFim;
    @Column(nullable = false)
    @NotNull
    @Positive
    private float valorMensalidade;

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public float getValorMensalidade() {
        return valorMensalidade;
    }

    public void setValorMensalidade(float valorMensalidade) {
        this.valorMensalidade = valorMensalidade;
    }
    
    
}
