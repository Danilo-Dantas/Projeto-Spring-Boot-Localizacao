package io.github.danilodantas.localizacao.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.danilodantas.localizacao.domain.entity.Cidade;
import io.github.danilodantas.localizacao.domain.repository.CidadeRepository;

@Service
public class CidadeService {

	private CidadeRepository cidadeRepository;
	
	public CidadeService(CidadeRepository cidadeRepository) {
		this.cidadeRepository = cidadeRepository;
	}
	
	@Transactional
	void salvarCidade() {
		var cidade = new Cidade(1L, "São Paulo", 12396372L);
		cidadeRepository.save(cidade);
	}
	
	public void listarCidadesPorNome() {
		cidadeRepository.findByNome("Porto Velho").forEach(System.out::println);
	}
	
	public void listarCidadesPorNomeComeca() {
		cidadeRepository.findByNomeStartingWith("Porto").forEach(System.out::println);
	}
	
	public void listarCidadesPorNomeTermina() {
		cidadeRepository.findByNomeEndingWith("a").forEach(System.out::println);
	}
	
	public void listarCidadesPorNomeContem() {
		cidadeRepository.findByNomeContaining("a").forEach(System.out::println);
	}
	
	public void listarCidadesPorNomeLike() {
		cidadeRepository.findByNomeLike("porto%").forEach(System.out::println);
	}
	
	public void listarCidadesPorNomeLikeSort() {
		cidadeRepository.findByNomeLikeSort("porto%", Sort.by("habitantes", "nome")).forEach(System.out::println);
	}
	
	public void listarCidadesPorNomeLikePage() {
		Pageable pageable = PageRequest.of(1, 4);
		cidadeRepository.findByNomeLikePage("%%%%%", pageable).forEach(System.out::println);
	}
	
	public void listarCidadesPorHabitantes() {
		cidadeRepository.findByHabitantes(78787900L).forEach(System.out::println);
	}
	
	public void listarCidadesPorQuantidadeHabitantesMenor() {
		cidadeRepository.findByHabitantesLessThan(1000001L).forEach(System.out::println);
	}
	
	public void listarCidadesPorQuantidadeHabitantesMenorIgual() {
		cidadeRepository.findByHabitantesLessThanEqual(1000001L).forEach(System.out::println);
	}
	
	public void listarCidadesPorQuantidadeHabitantesMaior() {
		cidadeRepository.findByHabitantesGreaterThan(1000001L).forEach(System.out::println);
	}
	
	public void listarCidade() {
		cidadeRepository.findAll().forEach(System.out::println);
	}
	
	public void listarCidadesPorQuantidadeHabitantesMenorEnome() {
		cidadeRepository.findByHabitantesLessThanAndNomeLike(1000001L, "Br%").forEach(System.out::println);
	}
	
	public List<Cidade> filtroDinamico(Cidade cidade) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.STARTING);
		Example<Cidade> example = Example.of(cidade, matcher);
		return cidadeRepository.findAll(example);
	}
}
