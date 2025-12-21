package br.com.kaiojoaorobert.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kaiojoaorobert.domain.entities.Cotacao;

public interface CotacaoRepository extends JpaRepository<Cotacao, UUID> {

}
