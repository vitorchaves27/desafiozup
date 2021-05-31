package br.com.estacionamento.dto;

import java.util.List;

public class ModelosFipeDto {
    List<AnoDTO> anos;
    List<ModelosDTO> modelos;

    public List<AnoDTO> getAnos() {
        return anos;
    }

    public void setAnos(List<AnoDTO> anos) {
        this.anos = anos;
    }

    public List<ModelosDTO> getModelos() {
        return modelos;
    }

    public void setModelos(List<ModelosDTO> modelos) {
        this.modelos = modelos;
    }
}
