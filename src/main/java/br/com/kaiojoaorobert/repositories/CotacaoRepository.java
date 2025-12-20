package br.com.kaiojoaorobert.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kaiojoaorobert.domain.entities.Cotacao;

public interface CotacaoRepository extends JpaRepository<Cotacao, UUID> {

    List<Cotacao> findByEmpresaId_Id(UUID empresaId);
}
