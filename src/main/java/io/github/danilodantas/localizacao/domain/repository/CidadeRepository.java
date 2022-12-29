package io.github.danilodantas.localizacao.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.danilodantas.localizacao.domain.entity.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	// Busca pelo nome correto
	List<Cidade> findByNome(String nome);
	
	// busca pelo nome começando por aquele pedaço
	List<Cidade> findByNomeStartingWith(String nome);
	
	// busca pelo nome terminando por aquele pedaço
	List<Cidade> findByNomeEndingWith(String nome);
	
	// busca pelo nome contendo aquele pedaço
	List<Cidade> findByNomeContaining(String nome);
	
	// busca por habitantes
	List<Cidade> findByHabitantes(Long habitantes);
}
