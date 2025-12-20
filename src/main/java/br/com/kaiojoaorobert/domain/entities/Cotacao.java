package br.com.kaiojoaorobert.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Cotacao {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String descricao;
    
    private String unidadeDeMedida;
    
    private String especificacoesTecnicas;
    

	private String nome;
	
	private List<Fornecedor> fornecedores = new ArrayList<>();
	
	@ManyToOne
	private Empresa empresaId;


}
