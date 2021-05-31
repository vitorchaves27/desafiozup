package br.com.estacionamento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.estacionamento.model.Cliente;
import br.com.estacionamento.service.ClienteService;
import br.com.estacionamento.controllers.request.ClienteDTO;

@RestController
@RequestMapping("cliente")
public class ClienteControle {
    @Autowired
    private ClienteService clienteService;


    @PostMapping(value = "/incluirCliente")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente incluirCliente(@RequestBody ClienteDTO cliente) {
        return clienteService.incluirClientes(cliente);

    }
}
