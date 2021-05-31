package br.com.estacionamento.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estacionamento.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	public Cliente findByCpf(String cpf);

}
