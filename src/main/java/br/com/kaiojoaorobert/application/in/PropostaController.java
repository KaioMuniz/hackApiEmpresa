package br.com.kaiojoaorobert.application.in;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kaiojoaorobert.configuration.JwtComponent;
import br.com.kaiojoaorobert.domain.dtos.BuscarPropostaResponse;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/propostas")
public class PropostaController {

	@Autowired JwtComponent jwt;
	
	@PostMapping
	public ResponseEntity<BuscarPropostaResponse> getProposta(@PathVariable UUID id, HttpServletRequest http) {
		
		UUID usuarioId = jwt.getUser(http);
		
		return null;
	}
}
 