package com.matheus.desafiotecnicomuralis.mapper.cliente;

import com.matheus.desafiotecnicomuralis.dto.cliente.ClienteDTO;
import com.matheus.desafiotecnicomuralis.entity.cliente.ClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public ClienteEntity map(ClienteDTO clienteDTO){
        return new ClienteEntity(
            clienteDTO.getId(),
            clienteDTO.getNome(),
            clienteDTO.getCpf(),
            clienteDTO.getData_nascimento(),
            clienteDTO.getEndereco(),
            clienteDTO.getListaContatos()
        );
    }

    public ClienteDTO map(ClienteEntity clienteEntity){
        return new ClienteDTO(
            clienteEntity.getId(),
            clienteEntity.getNome(),
            clienteEntity.getCpf(),
            clienteEntity.getData_nascimento(),
            clienteEntity.getEndereco(),
            clienteEntity.getListaContatos()
        );
    }
}
