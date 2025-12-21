package br.com.kaiojoaorobert.domain.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kaiojoaorobert.domain.dtos.CadastrarEmpresaRequest;
import br.com.kaiojoaorobert.domain.dtos.CadastrarEmpresaResponse;
import br.com.kaiojoaorobert.domain.entities.Empresa;
import br.com.kaiojoaorobert.domain.entities.Endereco;
import br.com.kaiojoaorobert.domain.exceptions.EmpresaJaCadastradaException;
import br.com.kaiojoaorobert.repositories.EmpresaRepository;

@Service
public class EmpresaServiceImpl {

	@Autowired EmpresaRepository empresaRepository;
	
	public CadastrarEmpresaResponse cadastrarEmpresa(CadastrarEmpresaRequest request, UUID usuarioId) {
		
		if(empresaRepository.existsByCnpj(request.getCnpj())) {
			throw new EmpresaJaCadastradaException();
		}
		if(empresaRepository.existsByUsuarioId(usuarioId)) {
			throw new EmpresaJaCadastradaException();
		}
		
		var empresa = new Empresa();
		empresa.setCnpj(request.getCnpj());
		empresa.setCotacoes(List.of());
		empresa.setEmail(request.getEmail());
		empresa.setRazaoSocial(request.getRazaoSocial());
		empresa.setTelefone(request.getTelefone());
		
		var endereco = new Endereco();
		endereco.setBairro(request.getEndereco().getBairro());
		endereco.setCep(request.getEndereco().getCep());
		endereco.setCidade(request.getEndereco().getCidade());
		endereco.setComplemento(request.getEndereco().getComplemento());
		endereco.setEstado(request.getEndereco().getEstado());
		endereco.setLogradouro(request.getEndereco().getLogradouro());
		endereco.setNumero(request.getEndereco().getNumero());
		
		empresa.setEndereco(endereco);
		
		empresa.setUsuarioId(usuarioId);
		
		empresaRepository.save(empresa);
		
		var resp = new CadastrarEmpresaResponse();
		
		resp.setIdEmpresa(empresa.getId());
		return resp;
	}
}
