package br.com.kaiojoaorobert.domain.exceptions;

@SuppressWarnings("serial")
public class EmpresaJaCadastradaException extends RuntimeException {

	@Override
	public String getMessage() {
		return "esta empresa já está cadastrada.";
	}
}
