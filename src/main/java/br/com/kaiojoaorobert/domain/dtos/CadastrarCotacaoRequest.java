package br.com.kaiojoaorobert.domain.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CadastrarCotacaoRequest {

	private String nome;
	private String descricao;

	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "A data deve estar no formato dd/MM/aaaa.")
	@NotEmpty(message = "Por favor, informe a data.")
	private String data;
	private UUID empresaId;
	private String status;

}
