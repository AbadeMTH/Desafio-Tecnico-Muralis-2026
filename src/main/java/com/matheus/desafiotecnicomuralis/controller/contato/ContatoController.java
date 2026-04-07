package com.matheus.desafiotecnicomuralis.controller.contato;

import com.matheus.desafiotecnicomuralis.service.contato.ContatoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService){
        this.contatoService = contatoService;
    }

    @GetMapping("/listar")
    public String listarContatos(){
        return contatoService.listarContatos();
    }
}
