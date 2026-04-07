package com.matheus.desafiotecnicomuralis.mapper.contato;

import com.matheus.desafiotecnicomuralis.dto.contato.ContatoDTO;
import com.matheus.desafiotecnicomuralis.entity.contato.ContatoEntity;
import org.springframework.stereotype.Component;

@Component
public class ContatoMapper {
    public ContatoEntity map(ContatoDTO contatoDTO){
        return new ContatoEntity(
            contatoDTO.getId(),
            contatoDTO.getTipo(),
            contatoDTO.getValor(),
            contatoDTO.getObservacao(),
            contatoDTO.getCliente()
        );
    }

    public ContatoDTO map(ContatoEntity contatoEntity){
        return new ContatoDTO(
            contatoEntity.getId(),
            contatoEntity.getTipo(),
            contatoEntity.getValor(),
            contatoEntity.getObservacao(),
            contatoEntity.getCliente()
        );
    }
}
