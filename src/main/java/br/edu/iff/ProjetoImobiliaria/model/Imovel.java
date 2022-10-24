package br.edu.iff.ProjetoImobiliaria.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Imovel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @NotBlank
    private String inscricao;
    @Column(nullable = false)
    @NotNull
    @Positive
    private float valorNegociacao;
    @Column(nullable = false)
    @NotBlank
    private String finalidade;
    @Column(nullable = false)
    @NotNull
    private boolean status;

//    @JsonManagedReference
//    @ManyToOne
//    @Valid
//    @NotNull
//    private Cliente cliente;
//    @Embedded
//    @OneToOne
//    @Valid
//    @NotNull
//    private Endereco endereco;
//    @JsonBackReference
//    @OneToMany(mappedBy = "imovel")
//    private List<Contrato> contratos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInscricao() {
        return inscricao;
    }

    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public float getValorNegociacao() {
        return valorNegociacao;
    }

    public void setValorNegociacao(float valorNegociacao) {
        this.valorNegociacao = valorNegociacao;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

//    public List<Contrato> getContratos() {
//        return contratos;
//    }
//
//    public void setContratos(List<Contrato> contratos) {
//        this.contratos = contratos;
//    }
//
//    public Endereco getEndereco() {
//        return endereco;
//    }
//
//    public void setEndereco(Endereco endereco) {
//        this.endereco = endereco;
//    }
//
//    public Cliente getCliente() {
//        return cliente;
//    }
//
//    public void setCliente(Cliente cliente) {
//        this.cliente = cliente;
//    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Imovel other = (Imovel) obj;
        return Objects.equals(this.id, other.id);
    }

}
