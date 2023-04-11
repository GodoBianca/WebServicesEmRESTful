package br.com.fiap.Oportuniza.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.Oportuniza.model.Empresa;
import br.com.fiap.Oportuniza.repository.EmpresaRepository;

@RestController
@RequestMapping("/empresa")

public class EmpresaController {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@GetMapping
	public List<Empresa> listarEmpresas() {
		return empresaRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Empresa adicionar(@RequestBody Empresa empresas) {
		return empresaRepository.save(empresas);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Empresa> atualizar(@PathVariable Long id, @RequestBody Empresa empresasAtualizado) {
	Optional<Empresa> empresaExistente = empresaRepository.findById(id);
	 if (empresaExistente.isPresent()) {
		 	Empresa empresa = empresaExistente.get();
		 	empresa.setNome(empresasAtualizado.getNome());
		 	empresa.setEmail(empresasAtualizado.getEmail());
		 	empresa.setCnpj(empresasAtualizado.getCnpj());
		 	empresaRepository.save(empresa);
		 	return ResponseEntity.ok(empresa);
		 } else {
		 	return ResponseEntity.notFound().build();
		 }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Empresa> excluir(@PathVariable Long id) {
	Optional<Empresa> empresa = empresaRepository.findById(id);
	 if (empresa.isPresent()) {
		 	empresaRepository.delete(empresa.get());
		 	return ResponseEntity.ok().build();
		 } else {
		 	return ResponseEntity.notFound().build();
		 }
	}
	
}
