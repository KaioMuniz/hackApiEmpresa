package br.com.kaiojoaorobert.domain.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class SolicitarFornecedorRequest {

	private UUID cotacaoId;
	private String texto;
}
