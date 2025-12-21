package br.com.kaiojoaorobert.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kaiojoaorobert.domain.entities.Proposta;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, UUID> {

}
