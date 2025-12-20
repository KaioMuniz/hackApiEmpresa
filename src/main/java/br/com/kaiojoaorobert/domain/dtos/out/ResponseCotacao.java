package br.com.kaiojoaorobert.domain.dtos.out;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.kaiojoaorobert.domain.entities.Empresa;
import br.com.kaiojoaorobert.domain.entities.Fornecedor;
import lombok.Data;

@Data
public class ResponseCotacao {
	
	private UUID id;
	private String descricao;
	private String unidadeDeMedida;
	private String especificacoesTecnicas;
	private String nome;
	private List<Fornecedor> fornecedores = new ArrayList<>();
	private Empresa empresaId;

}
