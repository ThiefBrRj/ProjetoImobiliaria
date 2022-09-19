package br.edu.iff.ProjetoImobiliaria.Repository;

import br.edu.iff.ProjetoImobiliaria.model.Aluga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlugaRepository extends JpaRepository<Aluga, Long> {

    Aluga findByContrato(int nContrato);

}
