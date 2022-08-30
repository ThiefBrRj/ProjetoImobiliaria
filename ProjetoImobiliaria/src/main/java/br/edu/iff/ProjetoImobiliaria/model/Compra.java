package br.edu.iff.ProjetoImobiliaria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra extends Contrato{
    @Column(nullable = false)
    @NotNull
    @Positive
    private float valorCompra;

    public float getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(float valorCompra) {
        this.valorCompra = valorCompra;
    }
    
}
