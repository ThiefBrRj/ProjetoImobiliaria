package br.edu.iff.ProjetoImobiliaria;

import br.edu.iff.ProjetoImobiliaria.repository.AlugaRepository;
import br.edu.iff.ProjetoImobiliaria.repository.ClienteRepository;
import br.edu.iff.ProjetoImobiliaria.repository.CompraRepository;
import br.edu.iff.ProjetoImobiliaria.repository.CorretorRepository;
import br.edu.iff.ProjetoImobiliaria.repository.ImovelRepository;
import br.edu.iff.ProjetoImobiliaria.model.Aluga;
import br.edu.iff.ProjetoImobiliaria.model.Cliente;
import br.edu.iff.ProjetoImobiliaria.model.Compra;
import br.edu.iff.ProjetoImobiliaria.model.Corretor;
import br.edu.iff.ProjetoImobiliaria.model.Endereco;
import br.edu.iff.ProjetoImobiliaria.model.Imovel;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjetoImobiliariaApplication {

    @Autowired
    ClienteRepository clienteRepo;
    @Autowired
    CorretorRepository corretorRepo;
    @Autowired
    ImovelRepository imovelRepo;
    @Autowired
    AlugaRepository alugaRepo;
    @Autowired
    CompraRepository compraRepo;

    public static void main(String[] args) {
        SpringApplication.run(ProjetoImobiliariaApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            Cliente c1 = new Cliente();
            c1.setNome("Joline");
            c1.setCpf("475.554.740-77");
            c1.setContaBancaria("122334");

            String t1 = "(22)999580010";
            String t2 = "(21)998531010";

            Endereco enderecoCliente = new Endereco();
            enderecoCliente.setRua("R. Isabel");
            enderecoCliente.setCidade("Campos");
            enderecoCliente.setNumero("41");
            enderecoCliente.setUf("RJ");
            enderecoCliente.setTipo("Casa");

            c1.setEnderecos(enderecoCliente);
            c1.setTelefone(t1);

            clienteRepo.save(c1);

            //Corretor
            Endereco end = new Endereco();
            end.setRua("R. Isabel");
            end.setCidade("Campos");
            end.setNumero("41");
            end.setUf("RJ");
            end.setTipo("Casa");

            c1.setEnderecos(end);

            Corretor f1 = new Corretor();
            f1.setNome("Josuke");
            f1.setCpf("532.960.510-58");
            f1.setGerenteId(1L);

            LocalDate hoje = LocalDate.now();
            f1.setDataAdmissao(hoje);

            f1.setEmail("josuke@gmail.com");
            f1.setEnderecos(end);
            f1.setTelefone(t2);

            corretorRepo.save(f1);

            //Imovel
            Imovel i1 = new Imovel();
            i1.setCliente(c1);
            i1.setEndereco(end);
            i1.setFinalidade("Alugar");
            i1.setStatus(true);
            i1.setValorNegociacao(2500f);
            i1.setInscricao("202011200064");

            imovelRepo.save(i1);

            Imovel i2 = new Imovel();
            i2.setCliente(c1);
            i2.setEndereco(end);
            i2.setFinalidade("Vender");
            i2.setStatus(true);
            i2.setValorNegociacao(250000f);
            i2.setInscricao("202011200065");

            imovelRepo.save(i2);

            //Aluga
            Aluga al1 = new Aluga();
            al1.setCliente(c1);
            al1.setCorretor(f1);
            al1.setDataContrato(hoje);
            al1.setDataInicio(hoje);
            al1.setDataFim(hoje);
            al1.setImovel(i1);
            al1.setNcontrato(001);
            al1.setValorMensalidade(i1.getValorNegociacao());

            alugaRepo.save(al1);

            //Compra
            Compra co1 = new Compra();
            co1.setCliente(c1);
            co1.setCorretor(f1);
            co1.setDataContrato(hoje);
            co1.setImovel(i2);
            co1.setNcontrato(2);
            co1.setValorCompra(i2.getValorNegociacao());

            compraRepo.save(co1);
        };
    }
}
