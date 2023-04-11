package br.com.fiap.Oportuniza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Oportuniza.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}