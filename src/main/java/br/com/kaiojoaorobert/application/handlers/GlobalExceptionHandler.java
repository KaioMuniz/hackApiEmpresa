package br.com.kaiojoaorobert.application.handlers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.kaiojoaorobert.domain.exceptions.CotacaoNaoEncontradaException;
import br.com.kaiojoaorobert.domain.exceptions.EmpresaJaCadastradaException;
import br.com.kaiojoaorobert.domain.exceptions.EmpresaNaoEncontradaException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CotacaoNaoEncontradaException.class)
	public ResponseEntity<Map<String, Object>> handleCotacaoNaoEncontradaException(CotacaoNaoEncontradaException ex, WebRequest request) {
		var body = new HashMap<String,Object>();
		body.put("timestamp", LocalDateTime.now());
		body.put("error", HttpStatus.BAD_REQUEST.value());
		body.put("message", ex.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmpresaJaCadastradaException.class)
	public ResponseEntity<Map<String, Object>> handleCotacaoNaoEncontradaException(EmpresaJaCadastradaException ex, WebRequest request) {
		var body = new HashMap<String,Object>();
		body.put("timestamp", LocalDateTime.now());
		body.put("error", HttpStatus.BAD_REQUEST.value());
		body.put("message", ex.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmpresaNaoEncontradaException.class)
	public ResponseEntity<Map<String, Object>> handleCotacaoNaoEncontradaException(EmpresaNaoEncontradaException ex, WebRequest request) {
		var body = new HashMap<String,Object>();
		body.put("timestamp", LocalDateTime.now());
		body.put("error", HttpStatus.BAD_REQUEST.value());
		body.put("message", ex.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
