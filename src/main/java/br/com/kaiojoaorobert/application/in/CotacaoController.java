package br.com.kaiojoaorobert.application.in;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kaiojoaorobert.configuration.JwtComponent;
import br.com.kaiojoaorobert.domain.dtos.BuscarCotacaoResponse;
import br.com.kaiojoaorobert.domain.dtos.CadastrarCotacaoRequest;
import br.com.kaiojoaorobert.domain.dtos.CadastrarCotacaoResponse;
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

        UUID usuarioId = jwtComponent.getUser(http);
        return ResponseEntity.ok(service.cadastrarCotacao(request, usuarioId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BuscarCotacaoResponse> consultarCotacao(@PathVariable UUID id, HttpServletRequest http) {
    	
    	UUID usuarioId = jwtComponent.getUser(http);
    	
    	return ResponseEntity.ok(service.consultar(id, usuarioId));
    }
}
