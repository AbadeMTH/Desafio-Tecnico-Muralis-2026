package com.matheus.desafiotecnicomuralis.service.cliente;

import com.matheus.desafiotecnicomuralis.dto.cliente.ClienteDTO;
import com.matheus.desafiotecnicomuralis.dto.contato.ContatoDTO;
import com.matheus.desafiotecnicomuralis.entity.cliente.ClienteEntity;
import com.matheus.desafiotecnicomuralis.entity.contato.ContatoEntity;
import com.matheus.desafiotecnicomuralis.mapper.cliente.ClienteMapper;
import com.matheus.desafiotecnicomuralis.repository.cliente.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteDTO> listarClientes(){
        List<ClienteEntity> listaClientesEntity = clienteRepository.findAll();
        return listaClientesEntity.stream()
                .map(clienteMapper::map)
                .collect(Collectors.toList());
    }
}
