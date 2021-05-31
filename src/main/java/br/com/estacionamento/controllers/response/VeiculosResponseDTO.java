package br.com.estacionamento.controllers.response;

public class VeiculosResponseDTO {

    private String ano;
    private String modelo;
    private String marca;
    private Boolean rodizioAtivo;
    private String valor;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() { return marca; }

    public void setMarca(String marca) { this.marca = marca; }

    public String getAno() { return ano; }

    public void setAno(String ano) { this.ano = ano; }

    public boolean getRodizioAtivo() { return rodizioAtivo; }

    public void setRodizioAtivo(Boolean rodizioAtivo) { this.rodizioAtivo = rodizioAtivo; }

    public String getValor() { return valor; }

    public void setValor(String valor) { this.valor = valor; }
}
