package com.matheus.desafiotecnicomuralis.repository.contato;

import com.matheus.desafiotecnicomuralis.entity.contato.ContatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {
}
