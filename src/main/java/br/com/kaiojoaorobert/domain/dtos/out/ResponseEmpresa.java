package br.com.kaiojoaorobert.domain.dtos.out;

import java.util.UUID;

import lombok.Data;

@Data
public class ResponseEmpresa {
	
	
	private UUID id;
    private String razaoSocial;
    private String cnpj;
    private String email;
    private String telefone;
    private UUID usuarioId;

}
