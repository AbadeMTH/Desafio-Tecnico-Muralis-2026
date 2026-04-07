package com.matheus.desafiotecnicomuralis.dto.contato;

import com.matheus.desafiotecnicomuralis.entity.cliente.ClienteEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContatoDTO {
    private Long id;
    private String tipo;
    private String nome;
    private String valor;
    private String observacao;
    private ClienteEntity cliente;
}
