package com.matheus.desafiotecnicomuralis.service.contato;

import com.matheus.desafiotecnicomuralis.dto.contato.ContatoDTO;
import com.matheus.desafiotecnicomuralis.entity.contato.ContatoEntity;
import com.matheus.desafiotecnicomuralis.mapper.contato.ContatoMapper;
import com.matheus.desafiotecnicomuralis.repository.contato.ContatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ContatoService {
    //Injetando repository
    private final ContatoRepository contatoRepository;
    private final ContatoMapper contatoMapper;

    public ContatoService(ContatoRepository contatoRepository, ContatoMapper contatoMapper){
        this.contatoRepository = contatoRepository;
        this.contatoMapper = contatoMapper;
    }

    public List<ContatoDTO> listarContatos(){
        List<ContatoEntity> listaContatosEntity = contatoRepository.findAll();
        return listaContatosEntity.stream()
                .map(contatoMapper::map)
                .collect(Collectors.toList());
    }
}
