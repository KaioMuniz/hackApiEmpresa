package br.com.kaiojoaorobert.domain.dtos.in;

import java.util.UUID;

import lombok.Data;

@Data
public class RequestEmpresa {
	
	private UUID id;
    private String razaoSocial;
    private String cnpj;
    private String email;
    private String telefone;
    private UUID usuarioId;
	
	

}
