package br.com.kaiojoaorobert.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kaiojoaorobert.domain.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, UUID> {

	 boolean existsByCnpj(String cnpj);

	 boolean existsByUsuarioId(UUID usuarioId);
	 
	 Optional<Empresa> findByUsuarioId(UUID usuarioId);
}
