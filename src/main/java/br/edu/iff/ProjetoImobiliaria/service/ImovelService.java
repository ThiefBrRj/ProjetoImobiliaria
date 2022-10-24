package br.edu.iff.ProjetoImobiliaria.service;

import br.edu.iff.ProjetoImobiliaria.exception.NotFoundException;
import br.edu.iff.ProjetoImobiliaria.repository.ImovelRepository;
import br.edu.iff.ProjetoImobiliaria.model.Imovel;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository repo;

    public List<Imovel> findAll() {
        return repo.findAll();
    }

    public Imovel findById(Long id) {
        Optional<Imovel> i = repo.findById(id);
        if (i.isEmpty()) {
            throw new NotFoundException("Imovel não encontrado.");
        }
        return i.get();
    }

    public Imovel save(Imovel i) {
        verificarDados(
//                i.getCliente(),
//                i.getEndereco(),
                i.getFinalidade(),
                i.getValorNegociacao(),
                i.getInscricao()
        );
        try {
            return repo.save(i);
        } catch (Exception e) {
            Throwable t = e;
            while (t.getCause() != null) {
                t = t.getCause();
                if (t instanceof ConstraintViolationException) {
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Erro ao salvar imóvel.");
        }
    }

    public Imovel update(Imovel i) {
        Imovel obj = findById(i.getId());
        verificarDados(
//                i.getCliente(),
//                i.getEndereco(),
                i.getFinalidade(),
                i.getValorNegociacao(),
                i.getInscricao()
        );
        try {
            i.setInscricao(obj.getInscricao());
            return repo.save(i);
        } catch (Exception e) {
            Throwable t = e;
            while (t.getCause() != null) {
                t = t.getCause();
                if (t instanceof ConstraintViolationException) {
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Erro ao atualizar imóvel.");
        }
    }

    public void delete(Long id) {
        Imovel i = findById(id);
        try {
            repo.delete(i);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar imóvel.");
        }
    }

    private void verificarDados(
//            Cliente cliente,
//            Endereco endereco,
            String finalidade,
            float valorNegociacao,
            String inscricao
    ) {
        if (inscricao.isBlank() || finalidade.isBlank()) {
            throw new IllegalArgumentException("Número de inscrição e/ou finalidade inválido(s).");
        }
//        if (endereco == null || cliente == null) {
//            throw new IllegalArgumentException("Cliente e endereço não podem ser nulos.");
//        }
        if (valorNegociacao <= 0) {
            throw new IllegalArgumentException("Valor de Negociação tem que ser positivo.");
        }
    }

}
