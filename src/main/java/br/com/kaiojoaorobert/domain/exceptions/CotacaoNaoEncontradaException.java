package br.com.kaiojoaorobert.domain.exceptions;

@SuppressWarnings("serial")
public class CotacaoNaoEncontradaException extends RuntimeException {

	@Override
	public String getMessage() {
		return "Cotação não encontrada, por favor verifique suas credenciais.";
	}
}
