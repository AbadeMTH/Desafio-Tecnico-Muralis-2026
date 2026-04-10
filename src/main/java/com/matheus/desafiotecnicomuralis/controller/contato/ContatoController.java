package com.matheus.desafiotecnicomuralis.controller.contato;

import com.matheus.desafiotecnicomuralis.dto.contato.ContatoDTO;
import com.matheus.desafiotecnicomuralis.service.contato.ContatoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes/{clienteId}/contatos")
public class ContatoController {
    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService){
        this.contatoService = contatoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ContatoDTO>> listarContatos(@PathVariable Long clienteId){
        return ResponseEntity.ok(contatoService.listarContatos(clienteId));
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarContato(@RequestBody ContatoDTO contatoDTO, @PathVariable Long clienteId){
        ContatoDTO contatoExisteOuNao = contatoService.criarContato(contatoDTO, clienteId);
        if(contatoExisteOuNao != null){
            return ResponseEntity.ok("Criado");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Contato ja existe ou cliente não existe");
    }

    @PatchMapping("/alterar")
    public ResponseEntity<?> alterarContato(@RequestBody ContatoDTO contatoDTO){
        ContatoDTO contato = contatoService.alterarContato(contatoDTO);
        if(contato != null){
            return ResponseEntity.ok(contato);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não existe");
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarContato(@PathVariable Long clienteId, @RequestBody ContatoDTO contatoDTO){
        boolean contatoExisteOuNao = contatoService.deletarContato(clienteId, contatoDTO);
        if(contatoExisteOuNao){
            return ResponseEntity.ok("Contato deletado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não existe");
    }
}
