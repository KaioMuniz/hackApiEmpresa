package br.com.kaiojoaorobert.domain.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.kaiojoaorobert.domain.dtos.in.RequestEmpresa;
import br.com.kaiojoaorobert.domain.dtos.out.ResponseEmpresa;
import br.com.kaiojoaorobert.domain.entities.Empresa;
import br.com.kaiojoaorobert.repositories.EmpresaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public ResponseEmpresa criar(RequestEmpresa request, UUID usuarioId) {
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial(request.getRazaoSocial());
        empresa.setCnpj(request.getCnpj());
        empresa.setEmail(request.getEmail());
        empresa.setTelefone(request.getTelefone());
        empresa.setUsuarioId(usuarioId);

        Empresa salva = empresaRepository.save(empresa);
        return toResponse(salva);
    }

    public ResponseEmpresa atualizar(UUID id, RequestEmpresa request, UUID usuarioId) {
        Empresa empresa = buscarEntidadePorId(id);

        if (!empresa.getUsuarioId().equals(usuarioId)) {
            throw new RuntimeException("Acesso negado");
        }

        empresa.setRazaoSocial(request.getRazaoSocial());
        empresa.setCnpj(request.getCnpj());
        empresa.setEmail(request.getEmail());
        empresa.setTelefone(request.getTelefone());

        return toResponse(empresaRepository.save(empresa));
    }

    public ResponseEmpresa buscarPorId(UUID id, UUID usuarioId) {
        Empresa empresa = buscarEntidadePorId(id);

        if (!empresa.getUsuarioId().equals(usuarioId)) {
            throw new RuntimeException("Acesso negado");
        }

        return toResponse(empresa);
    }

    public List<ResponseEmpresa> listarTodas(UUID usuarioId) {
        return empresaRepository.findAll()
                .stream()
                .filter(e -> e.getUsuarioId().equals(usuarioId))
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void deletar(UUID id, UUID usuarioId) {
        Empresa empresa = buscarEntidadePorId(id);

        if (!empresa.getUsuarioId().equals(usuarioId)) {
            throw new RuntimeException("Acesso negado");
        }

        empresaRepository.delete(empresa);
    }

    private Empresa buscarEntidadePorId(UUID id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
    }

    private ResponseEmpresa toResponse(Empresa empresa) {
        ResponseEmpresa response = new ResponseEmpresa();
        response.setId(empresa.getId());
        response.setRazaoSocial(empresa.getRazaoSocial());
        response.setCnpj(empresa.getCnpj());
        response.setEmail(empresa.getEmail());
        response.setTelefone(empresa.getTelefone());
        response.setUsuarioId(empresa.getUsuarioId());
        return response;
    }
}
