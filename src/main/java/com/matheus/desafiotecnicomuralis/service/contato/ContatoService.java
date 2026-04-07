package com.matheus.desafiotecnicomuralis.service.contato;

import com.matheus.desafiotecnicomuralis.repository.contato.ContatoRepository;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {
    //Injetando repository
    private final ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository){
        this.contatoRepository = contatoRepository;
    }

    public String listarContatos(){
        return "listar contatos";
    }
}
