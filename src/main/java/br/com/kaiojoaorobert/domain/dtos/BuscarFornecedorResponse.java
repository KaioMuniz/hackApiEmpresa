package br.com.kaiojoaorobert.domain.dtos;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class BuscarFornecedorResponse {

	private UUID id;
	private String nome;
	private String cnpj;
	private String telefone;
	private EnderecoResponse endereco;
	private List<String> contatos;

}
