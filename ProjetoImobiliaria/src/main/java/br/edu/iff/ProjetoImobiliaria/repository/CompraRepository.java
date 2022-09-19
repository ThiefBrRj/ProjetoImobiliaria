package br.edu.iff.ProjetoImobiliaria.repository;

import br.edu.iff.ProjetoImobiliaria.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    public Compra findByContrato(int nContrato);

}
