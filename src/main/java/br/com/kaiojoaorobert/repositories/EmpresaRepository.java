package br.com.kaiojoaorobert.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.kaiojoaorobert.domain.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, UUID> {

	@Query("""
			SELECT COUNT(e) 
			FROM empresa e
			WHERE e.cnpj = :cnpj
			""")
	boolean existsByCnpj(String cnpj);
	
	@Query("""
			SELECT COUNT(e)
			FROM empresa e
			WHERE e.usuarioId = :usuarioId
			""")
	boolean existsByUsuarioId(@Param("usuarioId") UUID usuarioId);
	
	Optional<Empresa> findByUsuarioId(UUID usuarioId);
}
