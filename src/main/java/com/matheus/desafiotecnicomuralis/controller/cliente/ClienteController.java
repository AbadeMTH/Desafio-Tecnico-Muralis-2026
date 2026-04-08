package com.matheus.desafiotecnicomuralis.controller.cliente;

import com.matheus.desafiotecnicomuralis.dto.cliente.ClienteDTO;
import com.matheus.desafiotecnicomuralis.service.cliente.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/listarPorCPF")
    public ResponseEntity<?> listarClientePorCPF(@RequestBody ClienteDTO clienteDTO){
        ClienteDTO clienteExisteOuNao = clienteService.listarClienteCPF(clienteDTO);
        if(clienteExisteOuNao != null){
            return ResponseEntity.status(HttpStatus.FOUND).body(clienteExisteOuNao);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarCliente(@RequestBody ClienteDTO clienteDTO){
        ClienteDTO clienteExisteOuNao = clienteService.criarCliente(clienteDTO);
        if(clienteExisteOuNao != null){
            return ResponseEntity.ok("Cliente criado");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body("Cliente ja existente com esse CPF");
    }

    @PatchMapping("/alterar/{id}")
    public ResponseEntity<?> alterarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        ClienteDTO contato = clienteService.alterarCliente(id, clienteDTO);
        if(contato != null){
            return ResponseEntity.ok(contato);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long id){
        boolean clienteExisteOuNao = clienteService.deletarCliente(id);
        if(clienteExisteOuNao){
            return ResponseEntity.ok("Cliente deletado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não existe");
    }
}
