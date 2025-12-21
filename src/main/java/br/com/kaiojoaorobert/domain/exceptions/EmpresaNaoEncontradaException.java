package br.com.kaiojoaorobert.domain.exceptions;

@SuppressWarnings("serial")
public class EmpresaNaoEncontradaException extends RuntimeException {

	@Override
	public String getMessage() {
		return "Empresa não encontrada, favor verifique as credenciais.";
	}
}
