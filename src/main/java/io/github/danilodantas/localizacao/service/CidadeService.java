package io.github.danilodantas.localizacao.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import io.github.danilodantas.localizacao.domain.entity.Cidade;
import io.github.danilodantas.localizacao.domain.repository.CidadeRepository;
import io.github.danilodantas.localizacao.domain.repository.projections.CidadeProjection;

import static io.github.danilodantas.localizacao.domain.repository.specs.CidadeSpecs.*;

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
	
	public void listarCidadesPorNomeSQL() {
		cidadeRepository
			.findByNomeSqlNativo("São Paulo")
			.stream().map(cidadeProjection -> new Cidade(cidadeProjection.getId(), cidadeProjection.getNome(),null))
			.forEach(System.out::println);
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
	
	public void listarCidadeByNomeSpec() {
		cidadeRepository
			.findAll(nomeEqual("São Paulo").and(habitantesGreaterThan(1000L)))
			.forEach(System.out::println);
 	}
	
	public void listarCidadeByNomeEhabitantesSpec() {
		cidadeRepository
			.findAll(nomeEqual("São Paulo").and(idEqual(1L)))
			.forEach(System.out::println);
 	}
	
	public void listarCidadeByHabitantesBetweenSpec() {
		cidadeRepository
			.findAll(habitantesBetween(23234780L, 100000000L))
			.forEach(System.out::println);
 	}
	
	public void listarCidadesSpecsFiltroDinamico(Cidade filtro) {
		Specification<Cidade> specs = Specification.where((root, query, cb) -> cb.conjunction());
		
		if (filtro.getId() != null) {
			specs = specs.and(idEqual(filtro.getId()));
			
		}
		
		if (StringUtils.hasText(filtro.getNome())) {
			specs = specs.and(nomeLike(filtro.getNome())); 
		}
		
		if (filtro.getHabitantes() != null) {
			specs = specs.and(habitantesGreaterThan(filtro.getHabitantes()));
		}
		
		cidadeRepository.findAll(specs).forEach(System.out::println);
	}
}
