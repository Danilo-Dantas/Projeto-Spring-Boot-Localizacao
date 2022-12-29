package io.github.danilodantas.localizacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import io.github.danilodantas.localizacao.domain.entity.Cidade;
import io.github.danilodantas.localizacao.domain.repository.CidadeRepository;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Override
	public void run(String... args) throws Exception {
		salvarCidade();
		listarCidade();
	}
	
	@Transactional
	void salvarCidade() {
		var cidade = new Cidade(1L, "São Paulo", 12396372L);
		cidadeRepository.save(cidade);
	}
	
	void listarCidade() {
		cidadeRepository.findAll().forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

}
