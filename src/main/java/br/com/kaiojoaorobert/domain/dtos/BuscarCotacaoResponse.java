package br.com.kaiojoaorobert.domain.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import br.com.kaiojoaorobert.domain.entities.StatusCotacao;
import lombok.Data;

@Data
public class BuscarCotacaoResponse {

	private UUID id;
	private String nome;

	private List<UUID> fornecedoresId;

	private List<UUID> propostasId;

	private UUID empresaId;

	private LocalDate dataLimite; // Para o sistema saber quando parar de aceitar propostas

	private StatusCotacao status = StatusCotacao.ABERTA;

	private String descricaoItens;
}
