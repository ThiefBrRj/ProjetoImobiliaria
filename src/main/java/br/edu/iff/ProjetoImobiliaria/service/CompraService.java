package br.edu.iff.ProjetoImobiliaria.service;

import br.edu.iff.ProjetoImobiliaria.exception.NotFoundException;
import br.edu.iff.ProjetoImobiliaria.repository.CompraRepository;
import br.edu.iff.ProjetoImobiliaria.model.Cliente;
import br.edu.iff.ProjetoImobiliaria.model.Compra;
import br.edu.iff.ProjetoImobiliaria.model.Corretor;
import br.edu.iff.ProjetoImobiliaria.model.Imovel;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    @Autowired
    private CompraRepository repo;

    public List<Compra> finfAll() {
        return repo.findAll();
    }

    public Compra findById(Long id) {
        Optional<Compra> c = repo.findById(id);
        if (c.isEmpty()) {
            throw new NotFoundException("Contarto de compra não encontrado.");
        }
        return c.get();
    }

    public Compra save(Compra c) {
        verificarDados(
                c.getCliente(),
                c.getCorretor(),
                c.getImovel(),
                c.getDataContrato(),
                c.getValorCompra()
        );
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
            throw new RuntimeException("Erro ao salvar contarto de compra.");
        }
    }

    public Compra update(Compra c) {
        Compra obj = findById(c.getId());
        verificarDados(
                c.getCliente(),
                c.getCorretor(),
                c.getImovel(),
                c.getDataContrato(),
                c.getValorCompra()
        );
        try {
            c.setNcontrato(obj.getNcontrato());
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
        Compra c = findById(id);
        try {
            repo.delete(c);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar contrato de compra.");
        }
    }

    private void verificarDados(
            Cliente cliente,
            Corretor corretor,
            Imovel imovel,
            LocalDate dataContrato,
            float valorCompra
    ) {
        if (corretor == null || cliente == null || imovel == null) {
            throw new IllegalArgumentException("Cliente, corretor e imóvel não podem ser nulos.");
        }
        if (valorCompra <= 0) {
            throw new IllegalArgumentException("Valor de compra tem que ser positivo.");
        }
        if (dataContrato == null) {
            throw new IllegalArgumentException("A data não pode ser nula.");
        }
        if (dataContrato.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data não pode ser anterior a hoje.");
        }

    }

}
