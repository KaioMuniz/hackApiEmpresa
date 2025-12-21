package br.com.kaiojoaorobert.application.in;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kaiojoaorobert.configuration.JwtComponent;
import br.com.kaiojoaorobert.domain.dtos.BuscarCotacaoResponse;
import br.com.kaiojoaorobert.domain.dtos.BuscarFornecedorResponse;
import br.com.kaiojoaorobert.domain.dtos.CadastrarCotacaoRequest;
import br.com.kaiojoaorobert.domain.dtos.CadastrarCotacaoResponse;
import br.com.kaiojoaorobert.domain.dtos.EditarCotacaoRequest;
import br.com.kaiojoaorobert.domain.dtos.EditarCotacaoResponse;
import br.com.kaiojoaorobert.domain.dtos.PropostaRequest;
import br.com.kaiojoaorobert.domain.dtos.PropostaResponse;
import br.com.kaiojoaorobert.domain.services.CotacaoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/cotacoes")
public class CotacaoController {

	@Autowired CotacaoServiceImpl service;
    @Autowired JwtComponent jwtComponent;

    @PostMapping
    public ResponseEntity<CadastrarCotacaoResponse> criar(
            @RequestBody CadastrarCotacaoRequest request,
            HttpServletRequest http) {

        return ResponseEntity.ok(service.cadastrarCotacao(request, getUsuarioId(http)));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BuscarCotacaoResponse> consultarCotacao(@PathVariable UUID id, HttpServletRequest http) {
    	
    		return ResponseEntity.ok(service.consultar(id, getUsuarioId(http)));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EditarCotacaoResponse> editCotacao(@PathVariable UUID id,@RequestBody EditarCotacaoRequest request, HttpServletRequest http) {
    	
    		return ResponseEntity.ok(service.editarCotacao(id, request, getUsuarioId(http)));
    }
    
    @PostMapping("/negociar/{id}")
    public ResponseEntity<PropostaResponse> negociarCotacao(@PathVariable UUID id, @RequestBody PropostaRequest request, HttpServletRequest http) {
    		return ResponseEntity.ok(service.negociarCotacao(id, request, getUsuarioId(http)));
    }
    
    @GetMapping("listar/fornecedores/{id}")
    public ResponseEntity<List<BuscarFornecedorResponse>> listarPorCotacao(@PathVariable UUID id, HttpServletRequest http) {
    	
    	
    		return ResponseEntity.ok(service.listarFornecedorPorCotacao(id, getUsuarioId(http)));
    }
    
    private UUID getUsuarioId(HttpServletRequest request) {
		
		var token = request.getHeader("Authorization").replace("Bearer ", "");
		
		return jwtComponent.getIdFromToken(token);
	}
}
