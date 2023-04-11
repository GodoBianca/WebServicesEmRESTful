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

import br.com.fiap.Oportuniza.model.Oportunidade;
import br.com.fiap.Oportuniza.repository.OportunidadeRepository;

@RestController
@RequestMapping("/oportunidade")

public class OportunidadeController {
		
		@Autowired
		private OportunidadeRepository oportunidadeRepository;
		
		@GetMapping
		public List<Oportunidade> listarOportunidades() {
			return oportunidadeRepository.findAll();
		}
		
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public Oportunidade adicionar(@RequestBody Oportunidade oportunidade) {
			return oportunidadeRepository.save(oportunidade);
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<Oportunidade> atualizar(@PathVariable Long id, @RequestBody Oportunidade oportunidadeAtualizado) {
		Optional<Oportunidade> oportunidadeExistente = oportunidadeRepository.findById(id);
		 if (oportunidadeExistente.isPresent()) {
			 	Oportunidade oportunidade = oportunidadeExistente.get();
			 	oportunidade.setNome(oportunidadeAtualizado.getNome());
			 	oportunidade.setDescricao(oportunidadeAtualizado.getDescricao());
			 	oportunidade.setLink(oportunidadeAtualizado.getDescricao());
			 	return ResponseEntity.ok(oportunidade);
			 } else {
			 	return ResponseEntity.notFound().build();
			 }
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<Oportunidade> excluir(@PathVariable Long id) {
		Optional<Oportunidade> usuario = oportunidadeRepository.findById(id);
		 if (usuario.isPresent()) {
			 	oportunidadeRepository.delete(usuario.get());
			 	return ResponseEntity.ok().build();
			 } else {
			 	return ResponseEntity.notFound().build();
			 }
		}
		
	}
