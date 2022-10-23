package br.edu.iff.ProjetoImobiliaria.service;

import br.edu.iff.ProjetoImobiliaria.exception.NotFoundException;
import br.edu.iff.ProjetoImobiliaria.repository.CorretorRepository;
import br.edu.iff.ProjetoImobiliaria.model.Corretor;
import br.edu.iff.ProjetoImobiliaria.model.Endereco;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorretorService {

    @Autowired
    private CorretorRepository repo;

    public List<Corretor> findAll() {
        return repo.findAll();
    }

    public Corretor findById(Long id) {
        Optional<Corretor> c = repo.findById(id);
        if (c.isEmpty()) {
            throw new NotFoundException("Corretor não encontardo.");
        }
        return c.get();
    }

    public Corretor save(Corretor c) {
        verificarDados(c.getCpf(), c.getNome(), c.getEndereco());
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
            throw new RuntimeException("Erro ao salvar corretor.");
        }
    }

    public Corretor update(Corretor c) {
        Corretor obj = findById(c.getId());
        verificarDados(
                c.getCpf(),
                c.getNome(),
                c.getEndereco()
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
            throw new RuntimeException("Erro ao atualizar corretor.");
        }
    }

    public void delete(Long id) {
        Corretor c = findById(id);
        try {
            repo.delete(c);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar corretor.");
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
