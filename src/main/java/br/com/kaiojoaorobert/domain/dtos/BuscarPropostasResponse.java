package br.com.kaiojoaorobert.domain.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class BuscarPropostasResponse {

	private UUID id;
	private UUID cotacaoId;
	private String texto;
}
