package br.com.kaiojoaorobert.domain.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Fornecedor {
	
	
	private UUID id;
	private String nome;
	private String cnpj;
	private Endereco endereco;
	private List<String> contatos;
	private List<Cotacao> cotacoes;

	
	

}
