package com.matheus.desafiotecnicomuralis.controller.cliente;

import com.matheus.desafiotecnicomuralis.dto.cliente.ClienteDTO;
import com.matheus.desafiotecnicomuralis.service.cliente.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ClienteDTO>> listarCliente(){
        return ResponseEntity.ok(clienteService.listarClientes());
    }
}
