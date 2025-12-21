package br.com.kaiojoaorobert.domain.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class PropostaResponse {

	private UUID id;
	private UUID idCotacao;
	private String texto;
}
