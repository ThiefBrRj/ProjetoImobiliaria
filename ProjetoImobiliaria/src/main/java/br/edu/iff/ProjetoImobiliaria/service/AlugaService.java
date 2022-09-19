package br.edu.iff.ProjetoImobiliaria.service;

import br.edu.iff.ProjetoImobiliaria.Repository.AlugaRepository;
import br.edu.iff.ProjetoImobiliaria.model.Aluga;
import br.edu.iff.ProjetoImobiliaria.model.Cliente;
import br.edu.iff.ProjetoImobiliaria.model.Corretor;
import br.edu.iff.ProjetoImobiliaria.model.Imovel;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlugaService {

    @Autowired
    private AlugaRepository repo;

    public List<Aluga> findAll() {
        return repo.findAll();
    }

    public Aluga findById(Long id) {
        Optional<Aluga> a = repo.findById(id);
        if (a.isEmpty()) {
            throw new RuntimeException("Contrato de aluguel não encontrado.");
        }
        return a.get();
    }

    public Aluga save(Aluga a) {
        verificarDados(
                a.getCliente(),
                a.getCorretor(),
                a.getImovel(),
                a.getDataContrato(),
                a.getDataInicio(),
                a.getDataFim(),
                a.getValorMensalidade()
        );
        try {
            return repo.save(a);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar Contrato de aluguel.");
        }
    }

    public Aluga update(Aluga a) {
        Aluga obj = findById(a.getId());
        verificarDados(
                a.getCliente(),
                a.getCorretor(),
                a.getImovel(),
                a.getDataContrato(),
                a.getDataInicio(),
                a.getDataFim(),
                a.getValorMensalidade()
        );
        try {
            a.setNcontrato(obj.getNcontrato());
            return repo.save(a);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar contrato de aluguel.");
        }
    }

    public void delete(Long id) {
        Aluga a = findById(id);
        try {
            repo.delete(a);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar contarto de aluguel.");
        }
    }

    private void verificarDados(
            Cliente cliente,
            Corretor corretor,
            Imovel imovel,
            LocalDate dataContrato,
            LocalDate dataInicio,
            LocalDate dataFim,
            float valorMensalidade
    ) {
        if (corretor == null || cliente == null || imovel == null) {
            throw new IllegalArgumentException("Cliente, corretor e imóvel não podem ser nulos.");
        }
        if (valorMensalidade <= 0) {
            throw new IllegalArgumentException("Valor de mensalidade tem que ser positivo.");
        }
        if (dataContrato == null || dataInicio == null || dataFim == null) {
            throw new IllegalArgumentException("As datas não podem ser nulas.");
        }
        if (dataFim.isBefore(dataContrato) || dataFim.isBefore(dataInicio) || dataInicio.isBefore(dataContrato)) {
            throw new IllegalArgumentException("Cronologia das datas está incorreta.");
        }
    }

}
