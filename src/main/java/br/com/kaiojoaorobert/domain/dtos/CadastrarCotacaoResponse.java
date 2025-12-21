package br.com.kaiojoaorobert.domain.dtos;

import java.time.LocalDate;
import java.util.UUID;

import br.com.kaiojoaorobert.domain.entities.StatusCotacao;
import lombok.Data;

@Data
public class CadastrarCotacaoResponse {

	private UUID id;
	private String nome;
	private LocalDate dataLimite;
	private StatusCotacao status;
}
