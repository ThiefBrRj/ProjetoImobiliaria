package br.edu.iff.ProjetoImobiliaria.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Corretor extends Pessoa {

    @Column(nullable = false)
    @NotBlank
    @Email
    private String email;
    @Column(nullable = false, columnDefinition = "DATE")
    @NotNull
    private LocalDate dataAdmissao;
    @Column(nullable = false)
    private Long gerenteId;

    @JsonBackReference
    @OneToMany(mappedBy = "corretor")
    private List<Contrato> contratos = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Long getGerenteId() {
        return gerenteId;
    }

    public void setGerenteId(Long gerenteId) {
        this.gerenteId = gerenteId;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

}
