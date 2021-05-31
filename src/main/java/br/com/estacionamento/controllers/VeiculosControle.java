package br.com.estacionamento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.estacionamento.model.Veiculos;
import br.com.estacionamento.service.VeiculosService;
import br.com.estacionamento.controllers.response.UsuarioDTO;
import br.com.estacionamento.controllers.request.VeiculosDTO;

@RestController
@RequestMapping("veiculos")
public class VeiculosControle {

    @Autowired
    private VeiculosService veiculoService;
    
    @GetMapping(value = "/buscaVeiculosPorCpf")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO buscaVeiculosPorCpf(@RequestParam String cpf) {
        return veiculoService.buscaVeiculosPorCpf(cpf);
    }

    @PostMapping(value = "/incluirVeiculos")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculos incluirVeiculos(@RequestBody VeiculosDTO veiculos) {
        return veiculoService.incluirVeiculos(veiculos);
    }
}
