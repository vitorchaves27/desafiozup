package br.com.estacionamento.dto;

public class ValorFipeDto {
    int AnoModelo;
    String CodigoFipe;
    String Combustivel;
    String Marca;
    String MesReferencia;
    String Modelo;
    String SiglaCombustivel;
    int TipoVeiculo;
    String Valor;

    public int getAnoModelo() {
        return AnoModelo;
    }

    public void setAnoModelo(int anoModelo) {
        AnoModelo = anoModelo;
    }

    public String getCodigoFipe() {
        return CodigoFipe;
    }

    public void setCodigoFipe(String codigoFipe) {
        CodigoFipe = codigoFipe;
    }

    public String getCombustivel() {
        return Combustivel;
    }

    public void setCombustivel(String combustivel) {
        Combustivel = combustivel;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getMesReferencia() {
        return MesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        MesReferencia = mesReferencia;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getSiglaCombustivel() {
        return SiglaCombustivel;
    }

    public void setSiglaCombustivel(String siglaCombustivel) {
        SiglaCombustivel = siglaCombustivel;
    }

    public int getTipoVeiculo() {
        return TipoVeiculo;
    }

    public void setTipoVeiculo(int tipoVeiculo) {
        TipoVeiculo = tipoVeiculo;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }
}