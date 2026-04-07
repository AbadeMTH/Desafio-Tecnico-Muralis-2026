package com.matheus.desafiotecnicomuralis.repository.contato;

import com.matheus.desafiotecnicomuralis.entity.contato.ContatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {
    Optional<ContatoEntity> findByNome(String nome_contato);
}
