package br.com.kaiojoaorobert.domain.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class BuscarPropostaResponse {

	private UUID id;
	private String texto;
	
	private UUID cotacaoId;
	
	private UUID usuarioId;
}
