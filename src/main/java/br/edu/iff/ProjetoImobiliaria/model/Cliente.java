package br.edu.iff.ProjetoImobiliaria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Cliente extends Pessoa {

    @Column(nullable = false, unique = true)
    @NotBlank
    private String contaBancaria;

//    @JsonBackReference
//    @OneToMany(mappedBy = "cliente")
//    private List<Imovel> imoveis = new ArrayList<>();
//    @JsonBackReference
//    @OneToMany(mappedBy = "cliente")
//    private List<Contrato> contratos = new ArrayList<>();

    public String getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(String contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

//    public List<Imovel> getImoveis() {
//        return imoveis;
//    }
//
//    public void setImoveis(List<Imovel> imoveis) {
//        this.imoveis = imoveis;
//    }
//
//    public List<Contrato> getContratos() {
//        return contratos;
//    }
//
//    public void setContratos(List<Contrato> contratos) {
//        this.contratos = contratos;
//    }

}
