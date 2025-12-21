package br.com.kaiojoaorobert.domain.dtos;

import lombok.Data;

@Data
public class CadastrarEmpresaRequest {

	private String razaoSocial;

	private String cnpj;

	private String email;

	private String telefone;

	private EnderecoRequest endereco;
}
