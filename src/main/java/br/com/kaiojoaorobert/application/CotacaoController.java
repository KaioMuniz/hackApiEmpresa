package br.com.kaiojoaorobert.application;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.kaiojoaorobert.configuration.JwtComponent;
import br.com.kaiojoaorobert.domain.dtos.in.RequestCotacao;
import br.com.kaiojoaorobert.domain.dtos.out.ResponseCotacao;
import br.com.kaiojoaorobert.domain.services.CotacaoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cotacoes")
@RequiredArgsConstructor
public class CotacaoController {

    private final CotacaoService cotacaoService;
    private final JwtComponent jwtComponent;

    @PostMapping
    public ResponseEntity<ResponseCotacao> criar(
            @RequestBody RequestCotacao request,
            HttpServletRequest http) {

        UUID usuarioId = jwtComponent.getUser(http);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cotacaoService.criar(request, usuarioId));
    }

    @GetMapping
    public ResponseEntity<List<ResponseCotacao>> listarPorEmpresa(HttpServletRequest http) {

        UUID usuarioId = jwtComponent.getUser(http);
        return ResponseEntity.ok(cotacaoService.listarPorEmpresa(usuarioId));
    }
}
