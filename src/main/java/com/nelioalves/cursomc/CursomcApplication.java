package com.nelioalves.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
@Autowired
	private CategoriaRepository catRepo;
@Autowired
private ProdutoRepository prodRepo;
@Autowired
private EstadoRepository estRepo;
@Autowired
private CidadeRepository cidRepo;
@Autowired
private ClienteRepository clienteRepo;
@Autowired
private EnderecoRepository endRepo;


	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Produto pr1 = new Produto(null, "Computador", 2000.00);
		Produto pr2 = new Produto(null, "Impressora", 800.00);
		Produto pr3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(pr1,pr2, pr3));
		cat2.getProdutos().addAll(Arrays.asList(pr2));
		
		pr1.getCategorias().addAll(Arrays.asList(cat1));
		pr2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		pr3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		
		Cidade c1 = new Cidade(null, "São Paulo", est2);
		Cidade c2 = new Cidade(null, "Uberlandia", est1);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		catRepo.saveAll(Arrays.asList(cat1,cat2));
		prodRepo.saveAll(Arrays.asList(pr1,pr2,pr3));
		
		estRepo.saveAll(Arrays.asList(est1,est2));
		cidRepo.saveAll(Arrays.asList(c1,c2,c3));
		Cliente cli1 = new Cliente(null, "Maria Silva","maria_silva@gmail.com", "66666666666", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("999953","546546566"));
		Endereco e1 = new Endereco(null, "Rua Flores", "300","apt 303" , "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105","sala 800" , "Centro", "38220834", cli1, c2);
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepo.save(cli1);
		endRepo.saveAll(Arrays.asList(e1, e2));
		
		
	}
}
