package br.edu.iff.ProjetoImobiliaria.repository;

import br.edu.iff.ProjetoImobiliaria.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {

    public Imovel findByInscricao(String inscricao);

}
