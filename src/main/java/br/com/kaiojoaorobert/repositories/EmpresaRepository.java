package br.com.kaiojoaorobert.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kaiojoaorobert.domain.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, UUID> {

}
