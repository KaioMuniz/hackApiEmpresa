package br.com.kaiojoaorobert.application.in;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kaiojoaorobert.configuration.JwtComponent;
import br.com.kaiojoaorobert.domain.dtos.BuscarPropostaResponse;
import br.com.kaiojoaorobert.domain.services.PropostaServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/propostas")
public class PropostaController {

	@Autowired
	JwtComponent jwt;
	
	@Autowired
	PropostaServiceImpl service;

	@GetMapping("/{id}")
	public ResponseEntity<List<BuscarPropostaResponse>> listPropostasPorCotacao(@PathVariable UUID id, HttpServletRequest http) {
		
		return ResponseEntity.ok(service.listByCotacao(id, getUsuarioId(http)));
	}

	private UUID getUsuarioId(HttpServletRequest request) {

		var token = request.getHeader("Authorization").replace("Bearer ", "");

		return jwt.getIdFromToken(token);
	}
}
