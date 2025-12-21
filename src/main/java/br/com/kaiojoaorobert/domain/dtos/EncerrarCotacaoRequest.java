package br.com.kaiojoaorobert.domain.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class EncerrarCotacaoRequest {

	private UUID vencedorId;
}
