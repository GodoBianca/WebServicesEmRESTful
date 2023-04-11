package br.com.fiap.Oportuniza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Oportuniza.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
