package br.com.kaiojoaorobert.application;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kaiojoaorobert.configuration.JwtComponent;
import br.com.kaiojoaorobert.domain.dtos.in.RequestEmpresa;
import br.com.kaiojoaorobert.domain.dtos.out.ResponseEmpresa;
import br.com.kaiojoaorobert.domain.services.EmpresaService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/empresas")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;
    private final JwtComponent jwtComponent;

    @PostMapping
    public ResponseEntity<ResponseEmpresa> criar(
            @RequestBody RequestEmpresa request,
            HttpServletRequest http) {

        UUID usuarioId = jwtComponent.getUser(http);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(empresaService.criar(request, usuarioId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEmpresa> atualizar(
            @PathVariable UUID id,
            @RequestBody RequestEmpresa request,
            HttpServletRequest http) {

        UUID usuarioId = jwtComponent.getUser(http);
        return ResponseEntity.ok(empresaService.atualizar(id, request, usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEmpresa> buscarPorId(
            @PathVariable UUID id,
            HttpServletRequest http) {

        UUID usuarioId = jwtComponent.getUser(http);
        return ResponseEntity.ok(empresaService.buscarPorId(id, usuarioId));
    }

    @GetMapping
    public ResponseEntity<List<ResponseEmpresa>> listarTodas(HttpServletRequest http) {

        UUID usuarioId = jwtComponent.getUser(http);
        return ResponseEntity.ok(empresaService.listarTodas(usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable UUID id,
            HttpServletRequest http) {

        UUID usuarioId = jwtComponent.getUser(http);
        empresaService.deletar(id, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
