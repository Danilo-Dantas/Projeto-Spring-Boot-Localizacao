package io.github.danilodantas.localizacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import io.github.danilodantas.localizacao.domain.entity.Cidade;
import io.github.danilodantas.localizacao.domain.repository.CidadeRepository;
import io.github.danilodantas.localizacao.service.CidadeService;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {
	
	@Autowired
	private CidadeService cidadeService; 
	
	@Override
	public void run(String... args) throws Exception {
		var cidade = new Cidade(1L, "São Paulo", 100L);
		cidadeService.listarCidadesSpecsFiltroDinamico(cidade);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

}
