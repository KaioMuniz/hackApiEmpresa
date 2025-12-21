package br.com.kaiojoaorobert.application.in;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fornecedores")
public class FornecedorController {

	@GetMapping
	public ResponseEntity<?> listAllFornecedores() {
		return null;
	}
}
