package br.com.estacionamento.controllers.response;

import java.util.List;

public class UsuarioDTO {

    private String nome;
    private String cpf;
    private String email;
    private String dataNascimento;
    private List<VeiculosResponseDTO> veiculos;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<VeiculosResponseDTO> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<VeiculosResponseDTO> veiculos) {
        this.veiculos = veiculos;
    }
}
