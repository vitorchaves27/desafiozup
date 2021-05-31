package br.com.estacionamento.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.com.estacionamento.dto.AnosFipeDto;
import br.com.estacionamento.dto.MarcasFipeDTO;
import br.com.estacionamento.dto.ModelosFipeDto;
import br.com.estacionamento.dto.ValorFipeDto;

@Component
public class FipeService {

    public MarcasFipeDTO getMarcaFipe() {
        return jsonToMarcaDto(clientGet("https://parallelum.com.br/fipe/api/v1/carros/marcas"));
    }

    public ModelosFipeDto getModeloFipe(String codigoMarca) {
        return jsonToModeloDto(clientGet("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + codigoMarca + "/modelos"));
    }

    public AnosFipeDto getAnoFipe(String codigoMarca, String codigoModelo) {
        return jsonToAnoDto(clientGet("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos"));
    }

    public ValorFipeDto getValorFipe(String codigoMarca, String codigoModelo, String codigoAno) {
        return jsonToValorDto(clientGet("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos/" + codigoAno));
    }

    private String clientGet(String urlGet) {
        String output;
        String json = "";
        try {
            URL url = new URL(urlGet);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200) {
                System.out.print("deu erro... HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            while ((output = br.readLine()) != null) {
                json += output;
            }
            System.out.println(json);
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

    private MarcasFipeDTO jsonToMarcaDto(String json) {
        Gson gson = new Gson();
        String newJson = "{ marcas: " + json + " }";
        MarcasFipeDTO res = gson.fromJson(newJson, MarcasFipeDTO.class);
        return res;
    }

    private ModelosFipeDto jsonToModeloDto(String json) {
        Gson gson = new Gson();
        ModelosFipeDto res = gson.fromJson(json, ModelosFipeDto.class);
        return res;
    }

    private AnosFipeDto jsonToAnoDto(String json) {
        Gson gson = new Gson();
        String newJson = "{ anos: " + json + " }";
        AnosFipeDto res = gson.fromJson(newJson, AnosFipeDto.class);
        return res;
    }

    private ValorFipeDto jsonToValorDto(String json) {
        Gson gson = new Gson();
        ValorFipeDto res = gson.fromJson(json, ValorFipeDto.class);
        return res;
    }
}
