package com.matheus.desafiotecnicomuralis.controller.contato;

import com.matheus.desafiotecnicomuralis.dto.contato.ContatoDTO;
import com.matheus.desafiotecnicomuralis.service.contato.ContatoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService){
        this.contatoService = contatoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ContatoDTO>> listarContatos(){
        return ResponseEntity.ok(contatoService.listarContatos());
    }
}
