package com.matheus.desafiotecnicomuralis.service.cliente;

import com.matheus.desafiotecnicomuralis.repository.cliente.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public String listarClientes(){
        return "listar clientes";
    }
}
