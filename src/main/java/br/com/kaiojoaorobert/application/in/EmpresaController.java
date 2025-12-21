package br.com.kaiojoaorobert.application.in;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kaiojoaorobert.configuration.JwtComponent;
import br.com.kaiojoaorobert.domain.dtos.CadastrarEmpresaRequest;
import br.com.kaiojoaorobert.domain.dtos.CadastrarEmpresaResponse;
import br.com.kaiojoaorobert.domain.services.EmpresaServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/empresas")
public class EmpresaController {

	@Autowired EmpresaServiceImpl service;
	@Autowired JwtComponent jwt;
	
	
	@PostMapping("/cadastrar")
	public ResponseEntity<CadastrarEmpresaResponse> cadastrarEmpresa(@RequestBody CadastrarEmpresaRequest request, HttpServletRequest http) {
		
		return ResponseEntity.ok(service.cadastrarEmpresa(request, getUsuarioId(http)));
	}
	
	private UUID getUsuarioId(HttpServletRequest request) {
		
		var token = request.getHeader("Authorization").replace("Bearer ", "");
		
		return jwt.getIdFromToken(token);
	}
}
