package io.github.danilodantas.localizacao.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.danilodantas.localizacao.domain.entity.Cidade;
import io.github.danilodantas.localizacao.domain.repository.projections.CidadeProjection;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

	@Query(nativeQuery = true, value = "select c.id_cidade as id, c.nome from tb_cidade as c where c.nome =:nome")
	List<CidadeProjection> findByNomeSqlNativo(@Param("nome") String nome);
	
	// Busca pelo nome correto
	List<Cidade> findByNome(String nome);
	
	// Busca pelo nome like ordenando por Sort
	@Query("select c from Cidade c where lower(c.nome) like lower(?1) ")
	List<Cidade> findByNomeLike(String nome);
	
	// Busca pelo nome like ordenando por Sort
	@Query("select c from Cidade c where lower(c.nome) like lower(?1) ")
	List<Cidade> findByNomeLikeSort(String nome, Sort sort);
	
	// Busca pelo nome like ordenando por page
	@Query("select c from Cidade c where lower(c.nome) like lower(?1) ")
	Page<Cidade> findByNomeLikePage(String nome, Pageable pageable);
	
	// busca pelo nome começando por aquele pedaço
	List<Cidade> findByNomeStartingWith(String nome);
	
	// busca pelo nome terminando por aquele pedaço
	List<Cidade> findByNomeEndingWith(String nome);
	
	// busca pelo nome contendo aquele pedaço
	List<Cidade> findByNomeContaining(String nome);
	
	// busca por habitantes
	List<Cidade> findByHabitantes(Long habitantes);
	
	// busca por quantidade de habitantes menor que ?
	List<Cidade> findByHabitantesLessThan(Long habitantes);
	
	// busca por quantidade de habitantes menor ou igual que ?
		List<Cidade> findByHabitantesLessThanEqual(Long habitantes);
	
	// busca por quantidade de habitantes maior que ?
	List<Cidade> findByHabitantesGreaterThan(Long habitantes);
	
	// busca por quantidade de habitantes menor que ? e o nome seja ?
	List<Cidade> findByHabitantesLessThanAndNomeLike(Long habitantes, String nome);
}
