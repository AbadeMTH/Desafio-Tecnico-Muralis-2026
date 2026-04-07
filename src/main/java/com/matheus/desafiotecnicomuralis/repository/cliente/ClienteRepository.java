package com.matheus.desafiotecnicomuralis.repository.cliente;

import com.matheus.desafiotecnicomuralis.dto.cliente.ClienteDTO;
import com.matheus.desafiotecnicomuralis.entity.cliente.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    Optional<ClienteDTO> findByCpf(String cpf);
}
