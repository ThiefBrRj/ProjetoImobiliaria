
package br.edu.iff.ProjetoImobiliaria.Repository;

import br.edu.iff.ProjetoImobiliaria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
    public Cliente findByCpf (String cpf);
        
}
