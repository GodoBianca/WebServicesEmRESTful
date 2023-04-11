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

import br.com.fiap.Oportuniza.model.Usuario;
import br.com.fiap.Oportuniza.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")

public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario adicionar(@RequestBody Usuario usuarios) {
		return usuarioRepository.save(usuarios);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuariosAtualizado) {
	Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
	 if (usuarioExistente.isPresent()) {
		 	Usuario usuario = usuarioExistente.get();
		 	usuario.setNome(usuariosAtualizado.getNome());
		 	usuario.setEmail(usuariosAtualizado.getEmail());
		 	usuario.setCpf(usuariosAtualizado.getCpf());
		 	usuarioRepository.save(usuario);
		 	return ResponseEntity.ok(usuario);
		 } else {
		 	return ResponseEntity.notFound().build();
		 }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> excluir(@PathVariable Long id) {
	Optional<Usuario> usuario = usuarioRepository.findById(id);
	 if (usuario.isPresent()) {
		 	usuarioRepository.delete(usuario.get());
		 	return ResponseEntity.ok().build();
		 } else {
		 	return ResponseEntity.notFound().build();
		 }
	}
	
}
