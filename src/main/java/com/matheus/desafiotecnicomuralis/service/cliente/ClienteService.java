package com.matheus.desafiotecnicomuralis.service.cliente;

import com.matheus.desafiotecnicomuralis.dto.cliente.ClienteDTO;
import com.matheus.desafiotecnicomuralis.dto.contato.ContatoDTO;
import com.matheus.desafiotecnicomuralis.entity.cliente.ClienteEntity;
import com.matheus.desafiotecnicomuralis.entity.contato.ContatoEntity;
import com.matheus.desafiotecnicomuralis.mapper.cliente.ClienteMapper;
import com.matheus.desafiotecnicomuralis.repository.cliente.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public ClienteDTO listarClientePorId(Long id){
        ClienteEntity clientePorId = clienteRepository.findById(id).orElse(null);

        if(clientePorId != null){
            return clienteMapper.map(clientePorId);
        }
        return null;
    }

    public ClienteDTO listarClientePorCPF(String cpf){
        ClienteEntity clientePorCPF = clienteRepository.findByCpf(cpf).orElse(null);

        if(clientePorCPF != null){
            return clienteMapper.map(clientePorCPF);
        }
        return null;
    }

    public ClienteDTO criarCliente(ClienteDTO clienteDTO){
        Optional<ClienteEntity> clienteExisteOuNao = clienteRepository.findByCpf(clienteDTO.getCpf());
        if(clienteExisteOuNao.isEmpty()){
            ClienteEntity novoCliente = clienteMapper.map(clienteDTO);
            novoCliente = clienteRepository.save(novoCliente);
            return clienteMapper.map(novoCliente);
        }
        return null;
    }

    public ClienteDTO alterarCliente(Long id, ClienteDTO clienteDTO){
        ClienteEntity clienteParaAlterar = clienteRepository.findById(id).orElse(null);

        if(clienteParaAlterar != null){
            if(clienteDTO.getData_nascimento() != null){
                clienteParaAlterar.setData_nascimento(clienteDTO.getData_nascimento());
            }

            if(clienteDTO.getEndereco() != null){
                clienteParaAlterar.setEndereco(clienteDTO.getEndereco());
            }

            if(clienteDTO.getCpf() != null){
                clienteParaAlterar.setCpf(clienteDTO.getCpf());
            }

            if(clienteDTO.getNome() != null){
                clienteParaAlterar.setNome(clienteDTO.getNome());
            }
            clienteParaAlterar = clienteRepository.save(clienteParaAlterar);
            return clienteMapper.map(clienteParaAlterar);
        }
        return null;
    }

    public boolean deletarCliente(Long id){
        Optional<ClienteEntity> clienteExisteOuNao = clienteRepository.findById(id);
        if(clienteExisteOuNao.isPresent()){
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
