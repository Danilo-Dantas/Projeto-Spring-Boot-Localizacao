package io.github.danilodantas.localizacao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.danilodantas.localizacao.domain.entity.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
