package br.com.estacionamento.dto;

import java.util.List;
public class MarcasFipeDTO {
    List<MarcaDTO> marcas;

    public List<MarcaDTO> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<MarcaDTO> marcas) {
        this.marcas = marcas;
    }
}
