package br.com.estacionamento.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private String dataNascimento;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getDataNascimento() { return dataNascimento; }
	public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

}
