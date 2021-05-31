package br.com.estacionamento.controllers.request;

public class VeiculosDTO {

    private String ano;
    private String modelo;
    private String marca;
    private String cpf;
    private String valorFipe;

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getValorFipe() { return valorFipe; }

    public void setValorFipe(String valorFipe) { this.valorFipe = valorFipe; }
}
