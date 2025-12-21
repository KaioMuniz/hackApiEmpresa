package br.com.kaiojoaorobert.application.scheduled;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.kaiojoaorobert.domain.entities.Cotacao;
import br.com.kaiojoaorobert.domain.entities.StatusCotacao;
import br.com.kaiojoaorobert.repositories.CotacaoRepository;

@Component
public class DatabaseComponent {

	@Autowired CotacaoRepository cotacaoRepository;
	
	@Scheduled(fixedRate = 6000)
	private void cancelarCotacoesNoLimite() {
		
		var cotacoes = cotacaoRepository.findAll();
		
		for(Cotacao cotacao : cotacoes) {
			if(cotacao.getDataLimite().isBefore(LocalDate.now())) {
				cotacao.setStatus(StatusCotacao.ENCERRADA);
				cotacaoRepository.save(cotacao);
			}
		}
		
	}
}
