package br.com.kaiojoaorobert.domain.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.kaiojoaorobert.domain.dtos.in.RequestCotacao;
import br.com.kaiojoaorobert.domain.dtos.out.ResponseCotacao;
import br.com.kaiojoaorobert.domain.entities.Cotacao;
import br.com.kaiojoaorobert.domain.entities.Empresa;
import br.com.kaiojoaorobert.repositories.CotacaoRepository;
import br.com.kaiojoaorobert.repositories.EmpresaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CotacaoService {

    private final CotacaoRepository cotacaoRepository;
    private final EmpresaRepository empresaRepository;

    public ResponseCotacao criar(RequestCotacao request, UUID usuarioId) {

        Empresa empresa = empresaRepository.findAll()
                .stream()
                .filter(e -> e.getUsuarioId().equals(usuarioId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada para este usuário"));

        Cotacao cotacao = new Cotacao();
        cotacao.setDescricao(request.getDescricao());
        cotacao.setUnidadeDeMedida(request.getUnidadeDeMedida());
        cotacao.setEspecificacoesTecnicas(request.getEspecificacoesTecnicas());
        cotacao.setNome(request.getNome());
        cotacao.setFornecedores(request.getFornecedores());
        cotacao.setEmpresaId(empresa);

        return toResponse(cotacaoRepository.save(cotacao));
    }

    public List<ResponseCotacao> listarPorEmpresa(UUID usuarioId) {

        Empresa empresa = empresaRepository.findAll()
                .stream()
                .filter(e -> e.getUsuarioId().equals(usuarioId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada para este usuário"));

        return cotacaoRepository.findByEmpresaId_Id(empresa.getId())
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private ResponseCotacao toResponse(Cotacao cotacao) {
        ResponseCotacao response = new ResponseCotacao();
        response.setId(cotacao.getId());
        response.setDescricao(cotacao.getDescricao());
        response.setUnidadeDeMedida(cotacao.getUnidadeDeMedida());
        response.setEspecificacoesTecnicas(cotacao.getEspecificacoesTecnicas());
        response.setNome(cotacao.getNome());
        response.setFornecedores(cotacao.getFornecedores());
        return response;
    }
}
