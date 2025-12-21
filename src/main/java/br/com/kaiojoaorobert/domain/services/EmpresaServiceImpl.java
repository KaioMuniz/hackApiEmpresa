package br.com.kaiojoaorobert.domain.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kaiojoaorobert.domain.dtos.CadastrarEmpresaRequest;
import br.com.kaiojoaorobert.domain.dtos.CadastrarEmpresaResponse;
import br.com.kaiojoaorobert.domain.dtos.CotacaoMessage;
import br.com.kaiojoaorobert.domain.dtos.SolicitarFornecedorRequest;
import br.com.kaiojoaorobert.domain.entities.Empresa;
import br.com.kaiojoaorobert.domain.entities.Endereco;
import br.com.kaiojoaorobert.domain.entities.Proposta;
import br.com.kaiojoaorobert.domain.entities.StatusCotacao;
import br.com.kaiojoaorobert.domain.exceptions.EmpresaJaCadastradaException;
import br.com.kaiojoaorobert.repositories.CotacaoRepository;
import br.com.kaiojoaorobert.repositories.EmpresaRepository;
import br.com.kaiojoaorobert.repositories.FornecedorRepository;
import br.com.kaiojoaorobert.repositories.PropostaRepository;
import br.com.kaiojoaorobert.repositories.UsuarioRepository;

@Service
public class EmpresaServiceImpl {

	@Autowired EmpresaRepository empresaRepository;
	@Autowired FornecedorRepository fornecedorRepository;
	@Autowired CotacaoRepository cotacaoRepository;
	@Autowired PropostaRepository propostaRepository;
	@Autowired UsuarioRepository usuarioRepository;
	
	public CadastrarEmpresaResponse cadastrarEmpresa(CadastrarEmpresaRequest request, UUID usuarioId) {
		
		if(empresaRepository.existsByCnpj(request.getCnpj())) {
			throw new EmpresaJaCadastradaException();
		}
		if(empresaRepository.existsByUsuarioId(usuarioId)) {
			throw new EmpresaJaCadastradaException();
		}
		
		var empresa = new Empresa();
		empresa.setCnpj(request.getCnpj());
		empresa.setCotacoes(List.of());
		empresa.setEmail(request.getEmail());
		empresa.setRazaoSocial(request.getRazaoSocial());
		empresa.setTelefone(request.getTelefone());
		
		var endereco = new Endereco();
		endereco.setBairro(request.getEndereco().getBairro());
		endereco.setCep(request.getEndereco().getCep());
		endereco.setCidade(request.getEndereco().getCidade());
		endereco.setComplemento(request.getEndereco().getComplemento());
		endereco.setEstado(request.getEndereco().getEstado());
		endereco.setLogradouro(request.getEndereco().getLogradouro());
		endereco.setNumero(request.getEndereco().getNumero());
		
		empresa.setEndereco(endereco);
		
		empresa.setUsuarioId(usuarioId);
		
		empresaRepository.save(empresa);
		
		var resp = new CadastrarEmpresaResponse();
		
		resp.setIdEmpresa(empresa.getId());
		return resp;
	}
	
	public String solicitarFornecedor(UUID idFornecedor, SolicitarFornecedorRequest request, UUID usuarioId) {
		var empresa = empresaRepository.findByUsuarioId(usuarioId).orElseThrow(() -> new RuntimeException("Empresa nao encontrada."));
		var cotacao = cotacaoRepository.findById(request.getCotacaoId()).orElseThrow(() -> new RuntimeException());
		var fornecedor = fornecedorRepository.findById(idFornecedor).orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
		
		if(!empresa.getCotacoes().contains(cotacao)) {
			throw new RuntimeException();
		}
		if(cotacao.getStatus().equals(StatusCotacao.CANCELADA) || cotacao.getStatus().equals(StatusCotacao.ENCERRADA)) {
			throw new RuntimeException();
		}
		
		var emailFornecedor = usuarioRepository.findById(fornecedor.getUsuarioId()).orElseThrow(() -> new RuntimeException());
		
		
		var proposta = new Proposta();
		proposta.setTexto(request.getTexto());
		proposta.setCotacao(cotacao);
		proposta.setUsuarioId(usuarioId);
		proposta.setFornecedor(fornecedor);
		
		propostaRepository.save(proposta);
		
		var message = new CotacaoMessage();
		message.setIdCotacao(cotacao.getId());
		message.setEmailEmpresa(empresa.getEmail());
		message.setEmailFornecedor(emailFornecedor.getEmail());
		
		return "Fornecedor solicitado";
	}
}
