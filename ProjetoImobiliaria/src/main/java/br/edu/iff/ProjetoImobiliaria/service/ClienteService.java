package br.edu.iff.ProjetoImobiliaria.service;

import br.edu.iff.ProjetoImobiliaria.Repository.ClienteRepository;
import br.edu.iff.ProjetoImobiliaria.model.Cliente;
import br.edu.iff.ProjetoImobiliaria.model.Endereco;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Cliente findById(Long id) {
        Optional<Cliente> c = repo.findById(id);
        if (c.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado");
        }
        return c.get();
    }

    public Cliente save(Cliente c) {
        verificarDados(c.getCpf(), c.getNome(), c.getEndereco());
        try {
            return repo.save(c);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar cliente.");
        }
    }

    public Cliente update(Cliente c) {
        Cliente obj = findById(c.getId());
        verificarDados(
                c.getCpf(),
                c.getNome(),
                c.getEndereco()
        );
        try {
            c.setCpf(obj.getCpf());
            return repo.save(c);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar cliente.");
        }
    }

    public void delete(Long id) {
        Cliente obj = findById(id);
        try {
            repo.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar cliente.");
        }
    }

    private void verificarDados(String cpf, String nome, Endereco endereco) {
        if (cpf.length() != 14 || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        if (nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido.");
        }
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço não pode ser nulo.");
        }
    }
}
