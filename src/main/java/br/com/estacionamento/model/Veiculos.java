package br.com.estacionamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Veiculos {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String marca;
	private String modelo;
	private String ano;
	private Long clienteId;
	private String valor;




	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	public String getMarca() { return marca; }
	public void setMarca(String marca) { this.marca = marca; }
	public String getAno() { return ano; }
	public void setAno(String ano) { this.ano = ano; }
	public String getValor() { return valor; }
	public void setValor(String valor) { this.valor = valor; }
}