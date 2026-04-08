package com.matheus.desafiotecnicomuralis.repository.contato;

import com.matheus.desafiotecnicomuralis.entity.contato.ContatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {
    Optional<ContatoEntity> findByValorAndClienteId(String valor,Long clienteId);
}
