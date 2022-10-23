package br.edu.iff.ProjetoImobiliaria.repository;

import br.edu.iff.ProjetoImobiliaria.model.Corretor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorretorRepository extends JpaRepository<Corretor, Long> {

    public Corretor findByCpf(String cpf);

}
