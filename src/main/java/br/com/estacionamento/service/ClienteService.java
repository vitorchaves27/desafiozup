package br.com.estacionamento.service;

import br.com.estacionamento.model.Cliente;
import br.com.estacionamento.controllers.request.ClienteDTO;
import br.com.estacionamento.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.*;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findCliente(String cpf) {

        Cliente clienteResultado = new Cliente();

        if (cpf != null && !cpf.equalsIgnoreCase("")) {
            clienteResultado = clienteRepository.findByCpf(cpf);
            if (clienteResultado != null) {
                return clienteResultado;
            }
        }

        return clienteResultado;

    }

    public Cliente incluirClientes(ClienteDTO clientes) {

        Cliente resultado = new Cliente();

        if (clientes.getCpf().isEmpty() || clientes.getDataNascimento().isEmpty() || clientes.getNome().isEmpty() || clientes.getEmail().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Os campo deve ser preenchido");

        }
            resultado = saveCliente(clientes);


        return resultado;
    }


    private Cliente saveCliente(ClienteDTO clientes) {

        Cliente clienteNovo = new Cliente();
    try{
        clienteNovo.setCpf(clientes.getCpf());
        clienteNovo.setNome(clientes.getNome());
        clienteNovo.setEmail(clientes.getEmail());
        clienteNovo.setDataNascimento(clientes.getDataNascimento());

        return clienteRepository.saveAndFlush(clienteNovo);

    }catch (Exception e){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cadastro incorreto");
    }

    }
}

