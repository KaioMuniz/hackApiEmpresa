package br.com.kaiojoaorobert.domain.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kaiojoaorobert.domain.dtos.BuscarPropostaResponse;
import br.com.kaiojoaorobert.domain.entities.Proposta;
import br.com.kaiojoaorobert.domain.exceptions.CotacaoNaoEncontradaException;
import br.com.kaiojoaorobert.repositories.CotacaoRepository;
import br.com.kaiojoaorobert.repositories.PropostaRepository;

@Service
public class PropostaServiceImpl {
	
	@Autowired PropostaRepository propostaRepository;
	@Autowired CotacaoRepository cotacaoRepository;
	
	public List<BuscarPropostaResponse> listByCotacao(UUID id, UUID usuarioId) {
		
		var cotacao = cotacaoRepository.findById(id).orElseThrow(() -> new CotacaoNaoEncontradaException());
		if(!cotacao.getEmpresa().getUsuarioId().equals(usuarioId)) {
			throw new CotacaoNaoEncontradaException();
		}
		
		var listPropostas = cotacao.getPropostas();
		
		return listPropostas.stream()
				.map(this::mapToOffer)
				.collect(Collectors.toList());
	}

	private BuscarPropostaResponse mapToOffer(Proposta proposta) {
		
		var resp = new BuscarPropostaResponse();
		resp.setId(proposta.getId());
		resp.setCotacaoId(proposta.getCotacao().getId());
		resp.setTexto(proposta.getTexto());
		resp.setUsuarioId(proposta.getUsuarioId());
		
		return resp;
	}
}
