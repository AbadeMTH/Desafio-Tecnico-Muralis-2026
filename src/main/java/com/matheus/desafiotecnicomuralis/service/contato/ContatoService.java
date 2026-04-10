package com.matheus.desafiotecnicomuralis.service.contato;

import com.matheus.desafiotecnicomuralis.dto.contato.ContatoDTO;
import com.matheus.desafiotecnicomuralis.entity.cliente.ClienteEntity;
import com.matheus.desafiotecnicomuralis.entity.contato.ContatoEntity;
import com.matheus.desafiotecnicomuralis.mapper.contato.ContatoMapper;
import com.matheus.desafiotecnicomuralis.repository.cliente.ClienteRepository;
import com.matheus.desafiotecnicomuralis.repository.contato.ContatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContatoService {
    //Injetando repository e mapper
    private final ContatoRepository contatoRepository;
    private final ClienteRepository clienteRepository;
    private final ContatoMapper contatoMapper;

    public ContatoService(ContatoRepository contatoRepository, ClienteRepository clienteRepository, ContatoMapper contatoMapper){
        this.contatoRepository = contatoRepository;
        this.clienteRepository = clienteRepository;
        this.contatoMapper = contatoMapper;
    }

    /**
     * Lista todos Contatos de um Cliente CASO ELE EXISTA, verifica existência pelo cliente id
     * @param clienteId Long - PathVariable
     * @return Lista de ContatoDTO
     */
    public List<ContatoDTO> listarContatos(Long clienteId){
        List<ContatoEntity> listaContatosEntity = contatoRepository.findAllByClienteId(clienteId).orElse(null);
        if(listaContatosEntity != null){
            return listaContatosEntity.stream()
                .map(contatoMapper::map)
                .collect(Collectors.toList());
        }
        return null;
    }

    /**
     * Cria um Contato CASO O CLIENTE EXISTA E O CONTATO NÃO EXISTA, verifica existência do Cliente pelo ID e verifica a existência do contado pelo Valor e ID do cliente para evitar duplicidade
     * @param contatoDTO ContatoDTO - RequestBody
     * @param id Long - PathVariable
     * @return ContatoDTO
     */
    public ContatoDTO criarContato(ContatoDTO contatoDTO, Long id){
        Optional<ClienteEntity> clienteExisteOuNao = clienteRepository.findById(id);
        if(clienteExisteOuNao.isPresent()){
            Optional<ContatoEntity> contatoExisteOuNao = contatoRepository.findByValorAndClienteId(contatoDTO.getValor(), id);
            if(contatoExisteOuNao.isEmpty()){
                ContatoEntity novoContato = contatoMapper.map(contatoDTO);
                novoContato.setCliente(new ClienteEntity());
                novoContato.getCliente().setId(id);
                novoContato = contatoRepository.save(novoContato);
                return contatoMapper.map(novoContato);
            }
            return null;
        }
        return null;
    }

    /**
     * Altera um contato CASO EXISTA, verifica existência pelo id
     * @param contatoDTO ContatoDTO - RequestBody
     * @return Caso alterou com sucesso retorna o Contato, caso contrário retorna null
     */
    public ContatoDTO alterarContato(ContatoDTO contatoDTO){
        ContatoEntity contatoParaAlterar = contatoRepository.findById(contatoDTO.getId()).orElse(null);

        if(contatoParaAlterar != null){

            if(contatoDTO.getValor() != null){
                contatoParaAlterar.setValor(contatoDTO.getValor());
            }

            if(contatoDTO.getObservacao() != null){
                contatoParaAlterar.setObservacao(contatoDTO.getObservacao());
            }

            if(contatoDTO.getTipo() != null){
                contatoParaAlterar.setTipo(contatoDTO.getTipo());
            }

            contatoParaAlterar = contatoRepository.save(contatoParaAlterar);
            return contatoMapper.map(contatoParaAlterar);
        }
        return null;
    }

    /**
     * Deleta um contato pelo id CASO EXISTA, verifica existência pelo id
     * @param clienteId Long - PathVariable
     * @param contatoDTO ContatoDTO - RequestBody
     * @return True se deletou, false se não deletou
     */
    public boolean deletarContato(Long clienteId, ContatoDTO contatoDTO){
        Optional<ContatoEntity> contatoExisteOuNao = contatoRepository.findByIdAndClienteId(contatoDTO.getId(), clienteId);
        if(contatoExisteOuNao.isPresent()){
            contatoRepository.deleteById(contatoDTO.getId());
            return true;
        }
        return false;
    }
}
