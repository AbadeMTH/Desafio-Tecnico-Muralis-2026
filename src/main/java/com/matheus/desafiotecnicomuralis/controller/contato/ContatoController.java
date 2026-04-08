package com.matheus.desafiotecnicomuralis.controller.contato;

import com.matheus.desafiotecnicomuralis.dto.contato.ContatoDTO;
import com.matheus.desafiotecnicomuralis.service.cliente.ClienteService;
import com.matheus.desafiotecnicomuralis.service.contato.ContatoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService, ClienteService clienteService){
        this.contatoService = contatoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ContatoDTO>> listarContatos(){
        return ResponseEntity.ok(contatoService.listarContatos());
    }

    @PostMapping("/criar/{clienteId}")
    public ResponseEntity<?> criarContato(@RequestBody ContatoDTO contatoDTO, @PathVariable Long clienteId){
        ContatoDTO contatoExisteOuNao = contatoService.criarContato(contatoDTO, clienteId);
        if(contatoExisteOuNao != null){
            return ResponseEntity.status(HttpStatus.CREATED).body("Criado");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body("Contato ja existe ou cliente não existe");
    }

    @PatchMapping("/alterar/{id}")
    public ResponseEntity<?> alterarContato(@PathVariable Long id, @RequestBody ContatoDTO contatoDTO){
        ContatoDTO contato = contatoService.alterarContato(id, contatoDTO);
        if(contato != null){
            return ResponseEntity.ok(contato);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não existe");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarContato(@PathVariable Long id){
        boolean contatoExisteOuNao = contatoService.deletarContato(id);
        if(contatoExisteOuNao){
            return ResponseEntity.ok("Contato deletado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não existe");
    }
}
