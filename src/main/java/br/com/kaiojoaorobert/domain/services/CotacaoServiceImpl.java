package br.com.kaiojoaorobert.domain.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kaiojoaorobert.domain.dtos.BuscarCotacaoResponse;
import br.com.kaiojoaorobert.domain.dtos.BuscarFornecedorResponse;
import br.com.kaiojoaorobert.domain.dtos.CadastrarCotacaoRequest;
import br.com.kaiojoaorobert.domain.dtos.CadastrarCotacaoResponse;
import br.com.kaiojoaorobert.domain.dtos.EditarCotacaoRequest;
import br.com.kaiojoaorobert.domain.dtos.EditarCotacaoResponse;
import br.com.kaiojoaorobert.domain.dtos.EnderecoResponse;
import br.com.kaiojoaorobert.domain.entities.Cotacao;
import br.com.kaiojoaorobert.domain.entities.Fornecedor;
import br.com.kaiojoaorobert.domain.entities.Proposta;
import br.com.kaiojoaorobert.domain.entities.StatusCotacao;
import br.com.kaiojoaorobert.domain.exceptions.CotacaoNaoEncontradaException;
import br.com.kaiojoaorobert.domain.exceptions.EmpresaNaoEncontradaException;
import br.com.kaiojoaorobert.repositories.CotacaoRepository;
import br.com.kaiojoaorobert.repositories.EmpresaRepository;

@Service
public class CotacaoServiceImpl {

	@Autowired CotacaoRepository cotacaoRepository;
	@Autowired EmpresaRepository empresaRepository;
	
	public CadastrarCotacaoResponse cadastrarCotacao(CadastrarCotacaoRequest request, UUID usuarioId) {
		
		var empresa = empresaRepository.findById(request.getEmpresaId()).orElseThrow(() -> new EmpresaNaoEncontradaException());
		if(!empresa.getUsuarioId().equals(usuarioId)) {
			throw new EmpresaNaoEncontradaException();
		}
		
		var cotacao = new Cotacao();
		cotacao.setNome(request.getNome());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate dataConvertida = LocalDate.parse(request.getData(), formatter);
		
		cotacao.setDataLimite(dataConvertida);
		cotacao.setDescricaoItens(request.getDescricao());
		cotacao.setFornecedores(List.of());
		cotacao.setPropostas(List.of());
		cotacao.setStatus(StatusCotacao.valueOf(request.getStatus()));
		
		cotacaoRepository.save(cotacao);
		
		empresa.getCotacoes().add(cotacao);
		
		empresaRepository.save(empresa);
		
		var resp = new CadastrarCotacaoResponse();
		resp.setId(cotacao.getId());
		resp.setDataLimite(cotacao.getDataLimite());
		resp.setNome(cotacao.getNome());
		resp.setStatus(cotacao.getStatus());
		
		return resp;
	}
	
	public BuscarCotacaoResponse consultar(UUID id, UUID usuarioId) {

		var cotacao = cotacaoRepository.findById(id).orElseThrow(() -> new CotacaoNaoEncontradaException());
		
		if(!cotacao.getEmpresa().getUsuarioId().equals(usuarioId)) {
			throw new CotacaoNaoEncontradaException();
		}
		
		List<UUID> listFornecedores = new ArrayList<>();
		List<UUID> listPropostas = new ArrayList<>();
		
		var response = new BuscarCotacaoResponse();
		response.setId(cotacao.getId());
		response.setDataLimite(cotacao.getDataLimite());
		response.setDescricaoItens(cotacao.getDescricaoItens());
		response.setEmpresaId(cotacao.getEmpresa().getId());
		response.setNome(cotacao.getNome());
		
		for(Fornecedor fornecedor: cotacao.getFornecedores()) {
			listFornecedores.add(fornecedor.getId());
		}
		for(Proposta proposta : cotacao.getPropostas()) {
			listPropostas.add(proposta.getId());
		}
		
		response.setFornecedoresId(listFornecedores);
		response.setPropostasId(listPropostas);
		
		return response;
		
	}
	
	public EditarCotacaoResponse editarCotacao(UUID id,EditarCotacaoRequest request, UUID usuarioId) {
		
		var cotacao = cotacaoRepository.findById(id).orElseThrow(() -> new CotacaoNaoEncontradaException());
		
		if(!cotacao.getEmpresa().getUsuarioId().equals(usuarioId)) {
			throw new CotacaoNaoEncontradaException();
		}
		
		cotacao.setNome(request.getNome());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate dataConvertida = LocalDate.parse(request.getData(), formatter);
		
		cotacao.setDataLimite(dataConvertida);
		cotacao.setDescricaoItens(request.getDescricao());
		cotacao.setStatus(StatusCotacao.valueOf(request.getStatus()));
		
		cotacaoRepository.save(cotacao);
		
		var resp = new EditarCotacaoResponse();
		resp.setId(cotacao.getId());
		resp.setNome(cotacao.getNome());
		resp.setDataLimite(cotacao.getDataLimite());
		resp.setStatus(cotacao.getStatus());
		
		return resp;
	}
	
	public List<BuscarFornecedorResponse> listarFornecedorPorCotacao(UUID id, UUID usuarioId) {
		
		var cotacao = cotacaoRepository.findById(id).orElseThrow(() -> new CotacaoNaoEncontradaException());
		
		if(!cotacao.getEmpresa().getUsuarioId().equals(usuarioId)) {
			throw new CotacaoNaoEncontradaException();
		}
		
		var listFornecedores = cotacao.getFornecedores();
		
		return listFornecedores.stream().map(this::mapToFornecedor)
				.collect(Collectors.toList());
		
	}
	
	private BuscarFornecedorResponse mapToFornecedor(Fornecedor fornecedor) {
		var resp = new BuscarFornecedorResponse();
		resp.setCnpj(fornecedor.getCnpj());
		resp.setContatos(fornecedor.getContatos());
		var enderecoResp = new EnderecoResponse();
		enderecoResp.setBairro(fornecedor.getEndereco().getBairro());
		enderecoResp.setCep(fornecedor.getEndereco().getCep());
		enderecoResp.setCidade(fornecedor.getEndereco().getCidade());
		enderecoResp.setComplemento(fornecedor.getEndereco().getComplemento());
		enderecoResp.setEstado(fornecedor.getEndereco().getEstado());
		enderecoResp.setLogradouro(fornecedor.getEndereco().getLogradouro());
		enderecoResp.setNumero(fornecedor.getEndereco().getNumero());
		resp.setEndereco(enderecoResp);
		resp.setId(fornecedor.getId());
		resp.setNome(fornecedor.getNome());
		resp.setTelefone(fornecedor.getTelefone());
		
		return resp;
	}
	
	
	public String encerrarCotacao(UUID id, UUID usuarioId) {
		
		var cotacao = cotacaoRepository.findById(id).orElseThrow(() -> new CotacaoNaoEncontradaException());
		
		if(!cotacao.getEmpresa().getUsuarioId().equals(usuarioId)) {
			throw new CotacaoNaoEncontradaException();
		}
		
		cotacao.setStatus(StatusCotacao.ENCERRADA);
		cotacaoRepository.save(cotacao);
		
		return "Cotação encerrada.";
	}
}