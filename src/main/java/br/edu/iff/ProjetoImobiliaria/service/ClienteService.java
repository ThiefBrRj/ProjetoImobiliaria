package br.edu.iff.ProjetoImobiliaria.service;

import br.edu.iff.ProjetoImobiliaria.exception.NotFoundException;
import br.edu.iff.ProjetoImobiliaria.repository.ClienteRepository;
import br.edu.iff.ProjetoImobiliaria.model.Cliente;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
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
            throw new NotFoundException("Cliente não encontrado");
        }
        return c.get();
    }

    public Cliente save(Cliente c) {
        verificarDados(c.getCpf(), c.getNome());
        try {
            return repo.save(c);
        } catch (Exception e) {
            Throwable t = e;
            while (t.getCause() != null) {
                t = t.getCause();
                if (t instanceof ConstraintViolationException) {
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Erro ao salvar cliente.");
        }
    }

    public Cliente update(Cliente c) {
        Cliente obj = findById(c.getId());
        verificarDados(
                c.getCpf(),
                c.getNome()
        );
        try {
            c.setCpf(obj.getCpf());
            return repo.save(c);
        } catch (Exception e) {
            Throwable t = e;
            while (t.getCause() != null) {
                t = t.getCause();
                if (t instanceof ConstraintViolationException) {
                    throw ((ConstraintViolationException) t);
                }
            }
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

    private void verificarDados(String cpf, String nome) {
        if (cpf.length() != 14 || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        if (nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido.");
        }
    }
}
